package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface CourseApi {

    @GetMapping(value = "/dummy")
    String dummy();
}
