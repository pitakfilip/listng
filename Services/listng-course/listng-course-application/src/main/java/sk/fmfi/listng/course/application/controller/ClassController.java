package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.ClassApi;
import sk.fmfi.listng.course.application.assembler.ClassAssembler;
import sk.fmfi.listng.course.application.repository.ClassRepository;
import sk.fmfi.listng.course.application.repository.RoomRepository;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.GroupService;
import sk.fmfi.listng.course.dto.ClassDto;
import sk.fmfi.listng.domain.administration.Class;
import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.domain.course.Course;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/class")
public class ClassController implements ClassApi {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private RoomRepository roomService;
    
    @Autowired 
    private GroupService groupService;
    
    @Autowired
    private ClassRepository repository;
    
    @Override
    public List<ClassDto> getByCourse(Long courseId) {
        Optional<Course> course = courseService.getById(courseId);
        
        if (course.isEmpty()) {
            throw new EntityNotFoundException("error.course.not.found");
        }
        
        List<Group> groups = course.get().getGroups();
        Set<ClassDto> classes = new HashSet<>();
        
        for (Group group : groups) {
            classes.addAll(ClassAssembler.toDto(group.getClasses().stream().toList()));
        }
        
        return new ArrayList<>(classes);
    }

    @Override
    public List<ClassDto> getByGroup(Long groupId) {
        List<Class> classes = repository.getClassesByGroupId(groupId);
        return classes.stream()
                .map(ClassAssembler::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassDto> getByRoom(Long roomId) {
        List<Class> classes = repository.getClassesByRoom_Id(roomId);
        return classes.stream()
                .map(ClassAssembler::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(List<ClassDto> classes) {
        for (ClassDto clazz : classes) {
            if (!roomService.existsById(clazz.room.id) || !groupService.groupExists(clazz.groupId)) {
                throw new EntityNotFoundException("error.class.FK.not.found");
            }
        }

        for (ClassDto dto : classes) {
            Class clazz = ClassAssembler.fromDto(dto);
            repository.save(clazz);
        }
    }

    @Override
    public void create(ClassDto clazz) {
        if (!roomService.existsById(clazz.room.id) || !groupService.groupExists(clazz.groupId)) {
            throw new EntityNotFoundException("error.class.FK.not.found");
        }

        Class obj = ClassAssembler.fromDto(clazz);
        repository.save(obj);
    }

    @Override
    public void update(List<ClassDto> classes) {
        for (ClassDto clazz : classes) {
            if (!repository.existsById(clazz.id)) {
                throw new EntityNotFoundException("error.class.not.found");
            }
        }

        for (ClassDto dto : classes) {
            Class clazz = ClassAssembler.fromDto(dto);
            repository.save(clazz);
        }
    }

    @Override
    public void delete(List<Long> classIds) {
        for (Long classId : classIds) {
            if (!repository.existsById(classId)) {
                throw new EntityNotFoundException("error.class.not.found");
            }
        }

        for (Long classId : classIds) {
            repository.deleteById(classId);   
        }
    }

    @Override
    public void delete(Long classId) {
        if (!repository.existsById(classId)) {
            throw new EntityNotFoundException("error.class.not.found");
        }
        repository.deleteById(classId);
    }
}
