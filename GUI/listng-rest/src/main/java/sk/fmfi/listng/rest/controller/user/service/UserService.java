package sk.fmfi.listng.rest.controller.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.rest.dto.UserBaseDto;
import sk.fmfi.listng.rest.dto.UserSaveDto;
import sk.fmfi.listng.rest.proxy.user.UserApiProxy;
import sk.fmfi.listng.user.dto.UsersOperationDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.enums.SystemRole;

import java.util.*;

@Service
public class UserService {
    
    @Autowired
    private UserApiProxy userApi;

    public void create(UsersOperationDto dto) {
        this.userApi.createUsers(dto);
    }
    
    public UserDto getUserById(Long userId) {
        return userApi.getUserById(userId);
    }
    
    public List<UserDto> getUsersById(List<Long> userIds) {
        return userApi.getUsersById(userIds);
    }
    
    public PageResponse<UserBaseDto> getUserPage(PagingParams params, boolean students) {
        PageResponse<UserDto> page = userApi.getUsers(params, students);
        return new PageResponse<>(page, 
                page.data.stream()
                        .map(UserBaseDto::of)
                        .toList()
        );
    }
    
    public void updateUser(UserSaveDto dto) {
        userApi.updateUser(dto.toUserDto());
    }

    public void updateUsers(UsersOperationDto usersOperationDto) {
        userApi.updateUsers(usersOperationDto);
    }
    
    public boolean deleteUsers(List<Long> userIds) {
        try {
            userApi.deleteUsers(userIds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
