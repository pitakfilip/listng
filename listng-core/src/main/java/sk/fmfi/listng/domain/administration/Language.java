package sk.fmfi.listng.domain.administration;

// Programatorsky jazyk
public class Language {

    private String name;

    private String suffix;

    public Language(String name, String suffix) {
        this.name = name;
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
