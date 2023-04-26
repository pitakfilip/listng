package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.ClassDto;

import java.util.List;

public interface ClassApi {
    
    @GetMapping(value = "/course")
    List<ClassDto> getByCourse(@RequestParam Long courseId);
    
    @GetMapping(value = "/group")
    List<ClassDto> getByGroup(@RequestParam Long groupId);
    
    @GetMapping(value = "/room")
    List<ClassDto> getByRoom(@RequestParam Long roomId);
    
    @PostMapping(value = "/s/new")
    void create(@RequestBody List<ClassDto> classes);

    @PostMapping(value = "/new")
    void create(@RequestBody ClassDto clazz);
    
    @PostMapping(value = "/update")
    void update(@RequestBody List<ClassDto> classes);
    
    @PostMapping(value = "/s/delete")
    void delete(@RequestBody List<Long> classIds);
    
    @DeleteMapping(value = "/delete")
    void delete(@RequestParam Long classId);
}
