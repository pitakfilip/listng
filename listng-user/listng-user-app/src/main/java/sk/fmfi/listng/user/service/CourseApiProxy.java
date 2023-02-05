package sk.fmfi.listng.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import sk.fmfi.listng.course.api.CourseApi;

@FeignClient(name = "listng-course", path = "/course", url = "localhost:8090")
public interface CourseApiProxy extends CourseApi {
}
