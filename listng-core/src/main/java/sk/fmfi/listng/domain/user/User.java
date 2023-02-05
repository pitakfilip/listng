package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.course.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentujúca užívateľa.
 */

public class User implements Serializable {
    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private List<Permission> permissions;

    public User(String name, String email, String password, int role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.of(role);
        this.permissions = new ArrayList<>();
    }

    public User() {
        this.permissions = new ArrayList<>();
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Permission getPermissionByCourse(Course course) {
        return permissions.stream().filter(p -> p.getCourse().equals(course)).findFirst().orElse(null);
    }

    public Role getRole() {
        return role;
    }

    public boolean isRoot() {
        return role == Role.ROOT;
    }

    public boolean isTeacher() {
        return role == Role.TEACHER;
    }

    public boolean isStudent() {
        return role == Role.STUDENT;
    }
}
