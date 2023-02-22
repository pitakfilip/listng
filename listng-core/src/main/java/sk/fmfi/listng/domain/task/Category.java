package sk.fmfi.listng.domain.task;

// hierarchia typov uloh e.g.:
// Java
//   - Collections
//        - List
//   - Stream
public class Category {

    private Long id;

    private String name;

    private Category parent;

    private int level; // je toto treba?

    public Category(String name) {
        this.name = name;
        this.level = 0;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        this.level = parent.level + 1;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
