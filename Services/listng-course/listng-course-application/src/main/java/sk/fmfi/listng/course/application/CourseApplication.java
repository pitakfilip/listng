package sk.fmfi.listng.course.application;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
@EnableFeignClients(basePackages = {"sk.fmfi.listng.course.application.proxy"})
public class CourseApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(CourseApplication.class, args);
    }
}

