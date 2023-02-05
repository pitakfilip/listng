package sk.fmfi.listng.course.controller;

import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.CourseApi;

@RestController
public class CourseController implements CourseApi {

    @Override
    public String dummy() {
        return "ale ahoj ferko!";
    }
}
