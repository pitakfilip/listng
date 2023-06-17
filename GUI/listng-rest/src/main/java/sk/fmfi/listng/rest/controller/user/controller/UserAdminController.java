package sk.fmfi.listng.rest.controller.user.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.infrastructure.enums.FileType;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.controller.user.service.PermissionService;
import sk.fmfi.listng.rest.controller.user.service.UserService;
import sk.fmfi.listng.rest.dto.ImportDto;
import sk.fmfi.listng.rest.dto.UserBaseDto;
import sk.fmfi.listng.rest.dto.UserSaveDto;
import sk.fmfi.listng.rest.util.XlsxImportParser;
import sk.fmfi.listng.user.dto.BulkOperationDto;
import sk.fmfi.listng.user.dto.UsersOperationDto;
import sk.fmfi.listng.user.dto.PermissionDto;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * Admin controller for handling requests for Users and Permissions.
 * Used for management of users (CREATE, UPDATE, DELETE) and their permissions.
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController extends ListController {
    
    @Autowired
    private UserService userService;    

    @Autowired
    private PermissionService permissionService;
    
    /**
     * Manually create new user accounts with specified permissions. By creating an account the new users
     * will have a generated password sent via email.
     * @param usersOperationDto Collection of new users (only name and email is processed) of a System Role and specified course permissions
     * @return Request handle successful or throw error
     */
    @PostMapping("/create")
    public Response createUsersManual(@RequestBody UsersOperationDto usersOperationDto) {
        this.userService.create(usersOperationDto);
        return success();
    }

    @PostMapping("/import/{type}/header")
    public Response<Map<Integer, String>> parseHeader(@RequestParam MultipartFile file, @PathVariable FileType type, int sheet, int row) {
        if (!type.equals(FileType.CSV) && !type.equals(FileType.XLSX)) {
            return error("file.upload.invalid.type");
        }
        
        Map<Integer, String> labels = null;
        if (type == FileType.XLSX) {
            labels = XlsxImportParser.getHeaderLabels(file, sheet, row);
        } else {
            // TODO podpora CSV
        }
        
        if (labels == null) {
            return error("file.parse.error");
        }
        return success(labels);
    }
    
    @PostMapping("/import/{type}/parse")
    public Response importUsers(@RequestPart("file") MultipartFile file, @RequestPart("config") ImportDto config, @PathVariable FileType type) {
        if (!type.equals(FileType.CSV) && !type.equals(FileType.XLSX)) {
            return error();
        }
        List<UserDto> newUsers = XlsxImportParser.parseUsers(file, config);
        
        UsersOperationDto usersOperationDto = new UsersOperationDto();
        usersOperationDto.role = config.role;
        usersOperationDto.users = newUsers;
        usersOperationDto.permissions = config.permissions;
        
        this.userService.create(usersOperationDto);
        return success();
    }
    
    /**
     * Get basic user data by user id.
     * @param   userId
     * @return  UserBaseDto of found user.
     */
    @GetMapping("/{userId}/user")
    public Response<UserBaseDto> getUserById(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);
        return success(UserBaseDto.of(user));
    }

    /**
     * Get list of users with basic data by id.
     * @param userIds   List of user ids.
     * @return          List of found users.
     */
    @PostMapping("/id/bulk")
    public Response<List<UserBaseDto>> getUsersById(@RequestBody List<Long> userIds) {
        List<UserDto> users = userService.getUsersById(userIds);
        return success(UserBaseDto.of(users));
    }
    
    // TODO Next-iteration: enable filtering with FilterParams structure
    /**
     * Get a collection of users for a table with pagination.
     * @param paging    Configuration of pagination and sorting.
     * @param type      'STUDENT' or 'TEACHER' expected. Determines whether the PageResponse should contain students or teachers (incl. administrators)
     * @return          Paging structure containing information for table accessories and a collection of filtered and sorted users.
     */
    @PostMapping("/{type}/page")
    public Response<PageResponse<UserBaseDto>> getUsersPage(@RequestBody PagingParams paging, @PathVariable String type) {
        PageResponse<UserBaseDto> page = userService.getUserPage(paging, type.equals("STUDENT"));
        return success(page);
    }
    
    /**
     * Get all course permissions for specified user.
     * @param userId
     * @return
     */
    @GetMapping("{userId}/permissions")
    public Response<List<PermissionDto>> getUserPermissions(@PathVariable Long userId) {
        return success(permissionService.getPermissions(userId));
    }

    /**
     * Update existing user. Permissions from the input are recognized as the final permissions the
     * user has assigned. This list is analyzed, where the missing permissions from the existing ones 
     * are removed from the database, existing ones are updated accordingly and non-existing permissions
     * are created.
     * @param   userSaveDto UserSaveDto containing updated user info and permissions separated in a list.
     * @return  Request handle successful or throw error
     */    
    @PostMapping("/update")
    public  Response updateUser(@RequestBody UserSaveDto userSaveDto){
        userService.updateUser(userSaveDto);
        return success();
    }

    /**
     * Update existing users where we are able to assign new permissions, remove existing permissions if applicable
     * and change their system role. <strong><u>Changing the system role will however reset their permissions</strong></u>, 
     * as they are not compatible between roles (student changed to teacher may not be a course attendee 
     * and teacher to student may not be a course owner).
     * @param usersOperationDto     UsersOperationDto containing updated user info and permissions separated in a list.
     * @return                      Request handle successful or throw error
     */
    @PostMapping("/update/bulk")
    public Response updateUsers(@RequestBody BulkOperationDto bulkOperationDto) {
        userService.updateUsers(bulkOperationDto);
        return success();
    }
    
    /**
     * Bulk delete of users.
     * @param userIds   List of user ids.
     * @return          Request handle successful or throw error
     */
    @PostMapping("/delete")
    public Response deleteUsers(@RequestBody List<Long> userIds) {
        return userService.deleteUsers(userIds) ? success() : error();
    }
}
