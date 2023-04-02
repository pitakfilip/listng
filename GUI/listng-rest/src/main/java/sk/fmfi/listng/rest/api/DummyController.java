package sk.fmfi.listng.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DummyController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/test")
    public String test() {
        return "TESTING MMMMMMMMMM NICE";
//        List<ServiceInstance> instances = discoveryClient.getInstances("listng-user");
//        System.out.println(instances.size());
//
//        instances.forEach(System.out::println);
//
//        loadBalancerClient.choose("listng-user");

    }
}
