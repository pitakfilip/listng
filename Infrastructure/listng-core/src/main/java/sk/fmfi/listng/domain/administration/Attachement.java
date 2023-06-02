package sk.fmfi.listng.domain.administration;

public class Attachement {

    private Long id;

    private String name;

    private String relativePath;

    public Attachement(String name, String relativePath) {
        this.name = name;
        this.relativePath = relativePath;
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

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
