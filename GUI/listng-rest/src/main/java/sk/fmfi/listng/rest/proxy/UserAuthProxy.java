package sk.fmfi.listng.rest.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import sk.fmfi.listng.user.api.UserApi;

@FeignClient(name = "listng-user")
public interface UserAuthProxy extends UserApi {
}
