package sk.fmfi.listng.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.List;

public interface UserApi {
    // TODO doplnit metody a prehodit anotacie z UserController + doplnit java doc ako v LVV
    
    @GetMapping("/auth")
    Response<UserAuthDto> getAuthUserByEmail(@RequestParam String email);

    @GetMapping("/email")
    Response<UserDto> getUserByEmail(@RequestParam String email);

    @GetMapping(value = "/all")
    Response<List<UserDto>> getAllUsers();
    
    @PostMapping("/new")
    Response newUser(@RequestBody UserAuthDto user);
    
    @GetMapping("/test")
    Response dummy();
}
