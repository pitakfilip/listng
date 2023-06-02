package sk.fmfi.listng.rest.proxy.user;

import org.springframework.cloud.openfeign.FeignClient;
import sk.fmfi.listng.user.api.UserAuthApi;

@FeignClient(name = "listng-user", path = "/user")
public interface UserAuthApiProxy extends UserAuthApi {
}

