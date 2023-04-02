package sk.fmfi.listng.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import sk.fmfi.listng.infrastructure.configuration.ListNGApplication;
import sk.fmfi.listng.infrastructure.configuration.PropertyInitializer;

@ListNGApplication
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class RestApplication {

    public static void main(String[] args) {
        PropertyInitializer.loadConsulProperties();
        SpringApplication.run(RestApplication.class, args);
    }
}
