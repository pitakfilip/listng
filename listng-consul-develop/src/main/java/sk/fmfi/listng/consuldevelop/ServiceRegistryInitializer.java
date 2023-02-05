package sk.fmfi.listng.consuldevelop;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

/**
 * Zaregistruje do lokalneho Consula mikrosluzby podla definicie v serviceRegistry.properties
 */
@Component
public class ServiceRegistryInitializer {

    @Resource(name = "servicesProperties")
    private Properties properties;

    @Inject
    private ConsulClient consulClient;

    public void initialize() {
        properties.values().stream()
                .map(String::valueOf)
                .map(ServiceRegistryInitializer::jsonToNewService)
                .forEach(consulClient::agentServiceRegister);
    }

    private static NewService jsonToNewService(String json) {
        NewService service = new NewService();

        service.setName(JsonPath.read(json, "$.name"));
        service.setAddress(JsonPath.read(json, "$.address"));
        service.setPort(JsonPath.read(json, "$.port"));

        // meta údaje musím naparsovať do mapy
        final Map<String, String> metaMap = ((List<String>) JsonPath.read(json, "$.meta"))
                .stream().
                collect(Collectors.toMap(item -> item.split("=")[0], item -> item.split("=")[1]));
        service.setMeta(metaMap);

        return service;
    }
}
