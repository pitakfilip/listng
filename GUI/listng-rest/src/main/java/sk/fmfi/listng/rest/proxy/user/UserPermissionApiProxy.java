package sk.fmfi.listng.rest.proxy.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "listng-user", path = "/user")
public interface UserPermissionApiProxy {

    @GetMapping(value = "/permission/request/attendee")
    void requestAccessToCourse(@RequestParam Long userId, @RequestParam Long courseId);

    @PostMapping(value = "/permission/role/{role}")
    void setUserRole(@RequestParam Long userId, @RequestParam Long courseId, @PathVariable String role);

    @PostMapping(value = "/permission/roles/{role}")
    void setUsersRole(@RequestBody List<Long> userIds, @RequestParam Long courseId, @PathVariable String role);

    @GetMapping(value = "/permission/status/{status}")
    void setUserStatus(@RequestParam Long userId, @RequestParam Long courseId, @PathVariable String status);

    @PostMapping(value = "/permission/status/{status}/bulk")
    void setUsersStatus(@RequestBody List<Long> userIds, @RequestParam Long courseId, @PathVariable String status);

    @GetMapping(value = "/permission/{role}/{status}")
    void setUserPermission(@RequestParam Long userId, @RequestParam Long courseId, @PathVariable String role, @PathVariable String status);

    @PostMapping(value = "/permission/{role}/{status}/bulk")
    void setUsersPermission(@RequestBody List<Long> userIds, @RequestParam Long courseId, @PathVariable String role, @PathVariable String status);

    @PostMapping(value = "/permission/group")
    void setGroupToUsers(@RequestBody List<Long> userIds, @RequestParam Long courseId, @RequestParam Long groupId);

    @GetMapping(value = "/permission/delete")
    void deletePermission(@RequestParam Long userId, @RequestParam Long courseId);

    @PostMapping(value = "/permission/delete/bulk")
    void deletePermissions(@RequestBody List<Long> userIds, @RequestParam Long courseId);
}
