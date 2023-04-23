package sk.fmfi.listng.rest.controller.payload.response;

import java.util.Set;

import sk.fmfi.listng.domain.enums.SystemRole;

public class UserInfo {

    private Long id;
    private String name;
    private String email;
    private SystemRole role;
    private Set<CoursePermission> permissions;

    public UserInfo(Long id, String name, String email, SystemRole role, Set<CoursePermission> permissions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SystemRole getRole() {
        return role;
    }

    public void setRole(SystemRole role) {
        this.role = role;
    }

    public Set<CoursePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<CoursePermission> permissions) {
        this.permissions = permissions;
    }
}
