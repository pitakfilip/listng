package sk.fmfi.listng.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RestApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.import", "optional:consul:");
        System.setProperty("spring.cloud.consul.config.format", "PROPERTIES");
        
        SpringApplication.run(RestApplication.class, args);
    }
}
