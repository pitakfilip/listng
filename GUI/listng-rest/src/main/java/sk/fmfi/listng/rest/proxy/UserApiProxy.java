package sk.fmfi.listng.rest.proxy;

import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.FeignLoadBalancerAutoConfiguration;
import sk.fmfi.listng.user.api.UserAuthApi;

@FeignClient(name = "listng-user", configuration = FeignLoadBalancerAutoConfiguration.class)
public interface UserApiProxy extends UserAuthApi {
}
