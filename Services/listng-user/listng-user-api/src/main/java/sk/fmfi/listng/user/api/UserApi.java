package sk.fmfi.listng.user.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;


public interface UserApi {
    
    @GetMapping("/base/login")
    @Deprecated
    UserAuthDto getAuthUserByEmail(@RequestParam("email") String email);
    
    @GetMapping("/base/email")
    UserDto getUserByEmail(@RequestParam String email);

//    @GetMapping("/id")
//    UserDto getUserById(@RequestParam Long id);
//
//    @PostMapping(value = "/all")
//    List<UserDto> getUsers(@RequestBody PagingRequest page);
//
//    @PostMapping("/update")
//    boolean saveUsers(@RequestBody List<UserDto> users);
//
//    @PostMapping("/delete")
//    boolean deleteUsers(@RequestBody List<UserDto> users);    
}
