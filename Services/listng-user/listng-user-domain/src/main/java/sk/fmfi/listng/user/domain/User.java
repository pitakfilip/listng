package sk.fmfi.listng.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Trieda reprezentujúca užívateľa.
 */
public class User implements Serializable {
    private Long id;

    private String name;

    private String email;

    private String password;

    private SystemRole role;

    private Set<Permission> permissions;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = SystemRole.nameOf(role);
        this.permissions = new HashSet<>();
    }

    public User(String name, String email, String password, SystemRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.permissions = new HashSet<>();
    }


    /**
     * @deprecated use implicit constructor instead.
     * This constructor shall be used only by ORM.
     */
    @Deprecated
    public User() {
        this.permissions = new HashSet<>();
    }

    public void addNewPermissions(List<Permission> permissions) {
        if (permissions != null) {
            this.permissions.addAll(permissions);
        }
    }

    public void removePermission(Permission permission) {
        if (permission != null) {
            this.permissions.remove(permission);
        }
    }

    public void findAndRemovePermissions(List<Long> courseIds) {
        List<Permission> found = permissions.stream()
                .filter(permission -> courseIds.contains(permission.getCourseId()))
                .toList();
        
        removePermissions(found);
    }

    /**
     * Search for permission of same course and update existing permission. 
     * If not found a new permission is created and added to users permission set.
     * @param save
     */
    public void savePermission(Permission save) {
        boolean found = false;
        for (Permission permission : permissions) {
            if (permission.getCourseId() == save.getCourseId()) {
                found = true;
                permission.setRole(save.getRole());
                permission.setStatus(save.getStatus());
                permission.setGroupId(save.getGroupId());
                break;
            }
        }
        if (!found) {
            permissions.add(new Permission(id, save.getCourseId(), save.getRole(), save.getStatus(), save.getGroupId()));
        }
    }
    
    public void removePermissions(List<Permission> permissions) {
        if (permissions != null) {
            for (Permission permission : permissions) {
                this.permissions.remove(permission);
            }
        }
    }
    
    public void clearPermissions() {
        this.permissions.clear();
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(SystemRole role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public SystemRole getRole() {
        return role;
    }

    public boolean isRoot() {
        return role == SystemRole.ROOT;
    }

    public boolean isTeacher() {
        return role == SystemRole.TEACHER;
    }

    public boolean isStudent() {
        return role == SystemRole.STUDENT;
    }
}
