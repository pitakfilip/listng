package sk.fmfi.listng.consuldevelop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication
public class ListngConsulDevelopApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(ListngConsulDevelopApplication.class);

    @Inject
    private ConsulStarter consulStarter;

    @Inject
    private ServiceRegistryInitializer serviceRegistryInitializer;

    @Inject
    private KeyValueStoreInitializer keyValueStoreInitializer;

    public static void main(String[] args) {
        SpringApplication.run(ListngConsulDevelopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Process process = consulStarter.start();

        serviceRegistryInitializer.initialize();
        keyValueStoreInitializer.initialize();

        logger.info("Consul on localhost configured and started successfully. UI runs on http://localhost:8500/.");

        process.waitFor();
    }
}
