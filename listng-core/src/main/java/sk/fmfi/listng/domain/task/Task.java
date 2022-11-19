package sk.fmfi.listng.domain.task;

import sk.fmfi.listng.domain.administration.Attachement;
import sk.fmfi.listng.domain.test.Test;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String name;

    private String description;

    private List<Attachement> images;

    private List<Attachement> files;

    private List<Category> categories;

    private List<Test> tests;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.images = new ArrayList<>();
        this.files = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.tests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attachement> getImages() {
        return images;
    }

    public void addImages(List<Attachement> imgs) {
        this.images.addAll(imgs);
    }

    public void removeImages(List<Attachement> imgs) {
        this.images.removeAll(imgs);
    }

    public List<Attachement> getFiles() {
        return files;
    }

    public void addFiles(List<Attachement> files) {
        this.files.addAll(files);
    }

    public void removeFiles(List<Attachement> files) {
        this.files.removeAll(files);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    public void removeCategories(List<Category> categories) {
        this.categories.removeAll(categories);
    }

    public void setImages(List<Attachement> images) {
        this.images = images;
    }

    public void setFiles(List<Attachement> files) {
        this.files = files;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void addTests(List<Test> tests) {
        this.tests.addAll(tests);
    }

    public void removeTests(List<Test> tests) {
        this.tests.removeAll(tests);
    }
}
