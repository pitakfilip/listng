package sk.fmfi.listng.course.api;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.ClassDto;

import java.util.List;

public interface ClassApi {
    
    @GetMapping(value = "/classes/course")
    List<ClassDto> getByCourse(@RequestParam Long courseId) throws NotFoundException;
    
    @GetMapping(value = "/classes/group")
    List<ClassDto> getByGroup(@RequestParam Long groupId);
    
    @GetMapping(value = "/classes/room")
    List<ClassDto> getByRoom(@RequestParam Long roomId);
    
    @PostMapping(value = "/classes/new")
    void create(@RequestBody List<ClassDto> classes) throws NotFoundException;

    @PostMapping(value = "/class/new")
    void create(@RequestBody ClassDto clazz) throws NotFoundException;
    
    @PostMapping(value = "/classes/update")
    void update(@RequestBody List<ClassDto> classes) throws NotFoundException;
    
    @PostMapping(value = "/classes/delete")
    void delete(@RequestBody List<Long> classIds) throws NotFoundException;
    
    @DeleteMapping(value = "/class/delete")
    void delete(@RequestParam Long classId) throws NotFoundException;
}
