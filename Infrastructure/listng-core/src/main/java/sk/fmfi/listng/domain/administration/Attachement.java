package sk.fmfi.listng.domain.administration;

public class Attachement {

    private Long id;

    private String name;

    private String path;

    public Attachement(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
