package sk.fmfi.listng.domain.test;

import sk.fmfi.listng.domain.administration.Attachement;

public class Test {

    private String name;

    private Attachement attachement;

    public Test(String name, Attachement attachement) {
        this.name = name;
        this.attachement = attachement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attachement getAttachement() {
        return attachement;
    }

    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }
}
