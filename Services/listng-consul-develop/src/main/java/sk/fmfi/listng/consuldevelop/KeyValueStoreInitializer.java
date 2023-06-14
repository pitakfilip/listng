package sk.fmfi.listng.consuldevelop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Ulozi do Key-Value Store-u lokalneho Consula konfiguracie jednotlivych mikrosluzieb
 * rovnakym sposobom, ako to robi git2consul.
 *
 * Konfiguracie nacitava z adresara configuration-properties, ktoreho obsah je zhodny
 * s obsahom Git repozitara, v ktorom su ulozene konfiguracie mikrosluzieb
 * pre DEV prostredie v PosAm cloude: https://git.posam.sk/ris-ng-deploy/properties/mur-psm-properties/-/tree/dev.
 *
 * Kazdy vyvojar si moze podla potreby konfiguracie lokalne (bez commitu) zmenit.
 */
@Component
public class KeyValueStoreInitializer {

    @Value("${consul.key-value.configurationRootDirectory}")
    private String rootDirectoryPathInClasspath;

    //data-key v key-value store je zhodne s nazvom suboru
    @Value("${consul.key-value.data-key}")
    private String propertiesFilename;

    @Value("${consul.key-value.keyPrefix}")
    private String configurationPrefix;

    @Inject
    private ConsulClient consulClient;

    public void initialize() throws IOException {
        for (Path microserviceDirectory : getMicroservicesDirectories()) {
            String key = configurationPrefix + microserviceDirectory.getFileName() + "/" + propertiesFilename;
            Path propertiesFile = microserviceDirectory.resolve(propertiesFilename);
            byte[] value = Files.readAllBytes(propertiesFile);

            consulClient.setKVBinaryValue(key, value);
        }
    }

    private List<Path> getMicroservicesDirectories() throws IOException {
        ClassPathResource rootDirectoryAsResource = new ClassPathResource(rootDirectoryPathInClasspath);
        Path rootDirectory = Paths.get(rootDirectoryAsResource.getURI());

        try (Stream<Path> list = Files.list(rootDirectory)) {
            return list
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(microserviceDirectory -> rootDirectory.resolve(microserviceDirectory))
                    .toList();
        }
    }
}
