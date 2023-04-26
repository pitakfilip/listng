package sk.fmfi.listng.user.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.user.api.UserPermissionApi;
import sk.fmfi.listng.user.dto.PermissionDto;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class UserPermissionController implements UserPermissionApi {
    
    
    @Override
    public void setPermissionToUser(PermissionDto permission) {
        
    }

    @Override
    public void setPermissionToUsers(List<PermissionDto> permissions) {

    }

    @Override
    public void removeUserPermissions(PermissionDto permission) {

    }

    @Override
    public void removeUsersPermissions(List<PermissionDto> permissions) {

    }

    @Override
    public void setGroupToUsers(List<Long> userIds, Long courseId, Long groupId) {

    }
}
