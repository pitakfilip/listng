package sk.fmfi.listng.infrastructure.configuration;


import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Default class to initialize system properties based on microservice context
 */
public final class PropertyInitializer {

    public PropertyInitializer() {}

    //TODO dorob dalsie zaujimave common properties
    public static void beforeSpringApplicationRun() {
        loadConsulProperties();
        loadHibernateMappings();
    }

    public static void loadConsulProperties() {
        System.setProperty("spring.config.import", "optional:consul:");
        System.setProperty("spring.cloud.consul.config.format", "PROPERTIES");
    }

    private static void loadHibernateMappings() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("hibernate");
        String path = url.getPath();

        List<String> hbmMappings = Stream.of(new File(path).listFiles())
                .map(file -> "hibernate/" + file.getName())
                .collect(Collectors.toList());

        String mappingResources = String.join(",", hbmMappings);

        System.setProperty("spring.jpa.mapping-resources", mappingResources);
    }
}
