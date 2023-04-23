package sk.posam.risng.mur.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author rucka
 */
@SpringBootApplication
@ImportResource("classpath:ctx/ctx-ui.xml")
@EnableDiscoveryClient
public class ListNGUiApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(ListNGUiApplication.class, args);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("/public/");

        registry.addResourceHandler("/public/index.html").addResourceLocations("/public/index.html")
                .setCachePeriod(0)
                .setCacheControl(CacheControl.noCache());

    }
}
