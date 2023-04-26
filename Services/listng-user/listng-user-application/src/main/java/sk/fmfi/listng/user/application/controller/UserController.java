package sk.fmfi.listng.user.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.infrastructure.common.dto.PagingRequest;
import sk.fmfi.listng.user.api.UserApi;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.service.UserService;


@RestController("/base")
@RequestMapping("/base")
public class UserController implements UserApi {
    
    @Autowired
    private UserService userService;

    @Override
    public UserAuthDto getAuthUserByEmail(String email) {
        User user = userService.getAuthByEmail(email);

        if (user == null) {
            return null;
        }
        return UserAssembler.toAuthDto(user);
    }
    
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        return UserAssembler.toDto(user);
    }

//    @Override
//    public UserDto getUserById(Long id) {
//        User user = userService.getUserById(id);
//        if (user == null) {
//            return null;
//        }
//
//        return UserAssembler.toDto(user);
//    }
//
//    @Override
//    public List<UserDto> getUsers(PagingRequest page) {
//        return null;
//    }
//
//    @Override
//    public boolean saveUsers(List<UserDto> users) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteUsers(List<UserDto> users) {
//        return false;
//    }

    
    
    
    
    
    
    
    
    
    
    
//    @Override
//    public Response<List<UserDto>> getAllUsers() {
//        List<User> users = userService.getUsers();
//
//        if (users == null) {
//            return error();
//        }
//
//        return success(UserAssembler.toDto(users));
//    }
//
//    @Override
//    public Response newUser(UserAuthDto dto) {
//        if (userService.exists(dto.email)) {
//            return error();
//        }
//        try {
//            User user = UserAssembler.fromAuthDto(dto);
//            userService.save(user);
//            return success();
//        } catch (Exception e) {
//            return error();
//        }
//    }
}
