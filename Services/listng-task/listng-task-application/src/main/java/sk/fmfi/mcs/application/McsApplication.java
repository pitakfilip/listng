package sk.fmfi.mcs.application;

import org.springframework.boot.SpringApplication;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
public class McsApplication {

    public static void main(String[] args) {
        PropertyInitializer.beforeSpringApplicationRun();
        SpringApplication.run(McsApplication.class, args);
    }
}
