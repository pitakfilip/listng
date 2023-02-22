package sk.fmfi.listng.application;

import org.springframework.boot.SpringApplication;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;

@ListNGApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}

