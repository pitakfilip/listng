package sk.fmfi.listng.domain.user;

import sk.fmfi.listng.domain.course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Trieda reprezentujúca úživateľa.
 */

public class User {

    private String name;

    private String email;

    private String password;

    private List<Permission> permissions;

    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.permissions = new ArrayList<>();
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
}
