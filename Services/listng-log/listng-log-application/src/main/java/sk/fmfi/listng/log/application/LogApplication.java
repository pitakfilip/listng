package sk.fmfi.listng.log.application;

import org.springframework.boot.SpringApplication;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
public class LogApplication {
    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(LogApplication.class, args);
    }
}
