package sk.fmfi.listng.course.application.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import sk.fmfi.listng.infrastructure.configuration.RoundRobinLoadBalancerConfiguration;
import sk.fmfi.listng.user.api.UserApi;

@FeignClient(name="listng-user", path="/user", configuration = RoundRobinLoadBalancerConfiguration.class)
public interface UserApiProxy extends UserApi {
}
