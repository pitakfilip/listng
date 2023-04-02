package sk.fmfi.listng.domain.taskset;

// priradeny typ v kurze a jej konfiguracia
// TODO premysliet ake konfiguracie typu mozu byt, okrem povolenie upload (min. body, ...)
public class CourseTaskSetType {

    private Long courseId;

    private Type type;

    private boolean uploadPermitted;

    public CourseTaskSetType(Long courseId, Type type, boolean uploadPermitted) {
        this.courseId = courseId;
        this.type = type;
        this.uploadPermitted = uploadPermitted;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isUploadPermitted() {
        return uploadPermitted;
    }

    public void setUploadPermitted(boolean uploadPermitted) {
        this.uploadPermitted = uploadPermitted;
    }
}
