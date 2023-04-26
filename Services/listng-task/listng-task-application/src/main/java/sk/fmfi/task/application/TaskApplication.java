package sk.fmfi.listng.task.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(TaskApplication.class, args);
    }
}

