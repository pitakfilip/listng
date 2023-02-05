package sk.fmfi.listng.consuldevelop;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ApplicationConfiguration {

    @Value("${consul.serviceRegistry.configurationFilename}")
    private String serviceRegistryPropertiesFilePathInClasspath;

    @Bean(name = "servicesProperties")
    protected PropertiesFactoryBean initServicesProperties() {
        PropertiesFactoryBean result = new PropertiesFactoryBean();
        result.setLocation(new ClassPathResource(serviceRegistryPropertiesFilePathInClasspath));
        return result;
    }

    @Bean
    protected ConsulClient initConsulClient() {
        return new ConsulClient("localhost");
    }
}
