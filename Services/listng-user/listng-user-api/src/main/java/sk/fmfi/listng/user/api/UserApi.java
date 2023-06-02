package sk.fmfi.listng.user.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.user.dto.UsersOperationDto;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.List;

public interface UserApi {
    
    @GetMapping("/base/email")
    UserDto getUserByEmail(@RequestParam String email);

    @GetMapping("/base/id")
    UserDto getUserById(@RequestParam Long id);
    
    @PostMapping("/base/ids")
    List<UserDto> getUsersById(@RequestBody List<Long> userIds);

    @PostMapping(value = "/base/all")
    PageResponse<UserDto> getUsers(@RequestBody PagingParams page, @RequestParam boolean students);
    
    @PostMapping("/base/create")
    void createUsers(@RequestBody UsersOperationDto usersOperationDto);
    
    @PostMapping("/base/update")
    void updateUser(@RequestBody UserDto userDto);
    
    @PostMapping("/base/update/bulk")
    void updateUsers(@RequestBody UsersOperationDto usersOperationDto);

    @PostMapping("/base/delete")
    void deleteUsers(@RequestBody List<Long> userIds);    
}
