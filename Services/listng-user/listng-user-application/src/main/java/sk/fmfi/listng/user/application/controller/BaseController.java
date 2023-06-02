package sk.fmfi.listng.user.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.infrastructure.common.dto.SortParams;
import sk.fmfi.listng.user.api.UserApi;
import sk.fmfi.listng.user.application.service.UserSaveService;
import sk.fmfi.listng.user.dto.UsersOperationDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.service.UserService;

import java.util.List;


@RestController
public class BaseController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSaveService userSaveService;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return null;
        }

        return UserAssembler.toDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return null;
        }

        return UserAssembler.toDto(user);
    }
    
    @Override 
    public List<UserDto> getUsersById(List<Long> ids) {
        return userService.getUsersById(ids);
    }

    @Override
    public PageResponse<UserDto> getUsers(PagingParams page, boolean students) {
        if (page.sort.isEmpty()) {
            page.sort.add(new SortParams("id"));
        }

        PageResponse<User> userPage = userService.getUsersPage(page.compile(), students);
        List<UserDto> dtos = userPage.data.stream()
                .map(UserAssembler::toDto)
                .toList();

        return new PageResponse<>(userPage, dtos);
    }

    @Override
    public void createUsers(UsersOperationDto usersOperationDto) {
        userSaveService.createUsers(usersOperationDto);
    }

    @Override
    public void updateUser(UserDto userDto) {
        if (userDto.id != null) {
            userSaveService.updateUser(userDto);
        }
    }

    @Override
    public void updateUsers(UsersOperationDto usersOperationDto) {
            userSaveService.updateUsers(usersOperationDto);
    }


    @Override
    public void deleteUsers(List<Long> userIds) {
        userService.delete(userIds);
    }

}
