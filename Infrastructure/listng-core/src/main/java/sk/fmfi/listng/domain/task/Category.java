package sk.fmfi.listng.domain.task;

import sk.fmfi.listng.domain.administration.MultiLangText;

// hierarchia typov uloh e.g.:
// Java
//   - Collections
//        - List
//   - Stream
public class Category {

    private Long id;

    private MultiLangText name;

    private Category parent;

    private int level; // je toto treba?

    public Category(MultiLangText name) {
        this.name = name;
        this.level = 0;
    }

    public Category(MultiLangText name, Category parent) {
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

    public MultiLangText getName() {
        return name;
    }

    public void setName(MultiLangText name) {
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
