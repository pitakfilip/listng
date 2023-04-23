package sk.fmfi.listng.user.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.infrastructure.common.BaseController;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.user.api.UserApi;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.service.UserService;

import java.util.List;

@RestController
public class UserController extends BaseController implements UserApi {
    
    @Autowired
    private UserService userService;

    @Override
    public Response<UserAuthDto> getAuthUserByEmail(String email) {
        User user = userService.getAuthByEmail(email);
        
        if (user == null) {
            return error();
        }
        return success(UserAssembler.toAuthDto(user));
    }

    @Override
    public Response<UserDto> getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return error();
        }
        
        return success(UserAssembler.toDto(user));
    }

    @Override
    public Response<List<UserDto>> getAllUsers() {
        List<User> users = userService.getUsers();

        if (users == null) {
            return error();
        }
        
        return success(UserAssembler.toDto(users));
    }

    @Override
    public Response newUser(UserAuthDto dto) {
        if (userService.exists(dto.email)) {
            return error();
        }
        try {
            User user = UserAssembler.newFromDto(dto);
            userService.save(user);
            return success();
        } catch (Exception e) {
            return error();
        }
    }
}
