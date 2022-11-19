package sk.fmfi.listng.domain.task;

public class Category {

    private String name;

    private Category parent;

    private int level;

    public Category(String name) {
        this.name = name;
        this.level = 0;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        this.level = parent.level + 1;
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
