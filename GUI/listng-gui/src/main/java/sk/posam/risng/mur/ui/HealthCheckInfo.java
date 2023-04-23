package sk.posam.risng.mur.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class HealthCheckInfo implements InfoContributor {

    private Map health;

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Autowired
    private HealthCheckMBean healthCheckMBean;

    @PostConstruct
    private void init() {
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        health = healthCheckMBean != null ? healthCheckMBean.toMap(objectMapper) : null;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetails(health);
    }
}
