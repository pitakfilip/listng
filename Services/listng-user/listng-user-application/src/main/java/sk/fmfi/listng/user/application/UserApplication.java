package sk.fmfi.listng.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(UserApplication.class, args);
    }
}

