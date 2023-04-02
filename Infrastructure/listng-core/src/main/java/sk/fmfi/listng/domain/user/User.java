package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.enums.SystemRole;
import sk.fmfi.listng.domain.utils.Encryption;

import java.io.Serializable;
import java.util.HashSet;
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

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = SystemRole.nameOf(role);
        this.permissions = new HashSet<>();
    }


    /** @deprecated use implicit constructor instead.
     * This constructor shall be used only by spring JPA.
     * By using this constructor we bypass the password encryption.
     * */
    @Deprecated
    public User() {
        this.permissions = new HashSet<>();
    }

    private boolean validatePass (String test) throws Exception {
        return Encryption.validate(test, this.password);
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

    public Permission getPermissionByCourse(long courseId) {
        return permissions.stream().filter(p -> p.getCourseId() == courseId).findFirst().orElse(null);
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
