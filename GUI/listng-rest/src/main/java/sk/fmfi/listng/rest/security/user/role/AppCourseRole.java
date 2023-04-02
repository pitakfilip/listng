package sk.fmfi.listng.rest.security.user.role;

public class AppCourseRole implements UserRole {
    
    private long courseId;
    
    private String role;

    public AppCourseRole(long courseId, String role) {
        this.courseId = courseId;
        this.role = role;
    }

    @Override
    public String getName() {
        return courseId + "/" + role;
    }
}
