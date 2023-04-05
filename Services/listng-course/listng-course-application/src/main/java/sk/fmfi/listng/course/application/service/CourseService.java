package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.repository.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
}
