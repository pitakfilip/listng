package sk.fmfi.listng.course.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(CourseApplication.class, args);
    }
}

