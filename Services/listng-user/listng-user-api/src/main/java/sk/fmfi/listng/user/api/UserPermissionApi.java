package sk.fmfi.listng.user.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.user.dto.PermissionDto;

import java.util.List;

/**
 * Api handling operations regarding
 */

public interface UserPermissionApi {

    @PostMapping(value = "/permission/set/one")
    void setPermissionToUser(@RequestBody PermissionDto permission);
    
    
    @PostMapping(value = "/permission/set/many")
    void setPermissionToUsers(@RequestBody List<PermissionDto> permissions);

    
    @PostMapping(value = "/permission/delete/one")
    void removeUserPermissions(@RequestBody PermissionDto permission);

    
    @PostMapping(value = "/permission/delete/many")
    void removeUsersPermissions(@RequestBody List<PermissionDto> permissions);
    
    
    @PostMapping(value = "/permission/group")
    void setGroupToUsers(@RequestBody List<Long> userIds, @RequestParam Long courseId, @RequestParam Long groupId);

}
