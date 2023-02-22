package sk.fmfi.listng.solution.application;

import org.springframework.boot.SpringApplication;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
public class SolutionApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(SolutionApplication.class, args);
    }
}
