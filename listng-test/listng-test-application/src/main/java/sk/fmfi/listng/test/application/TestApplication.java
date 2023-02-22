package sk.fmfi.listng.test.application;

import org.springframework.boot.SpringApplication;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
public class TestApplication {
    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(TestApplication.class, args);
    }
}
