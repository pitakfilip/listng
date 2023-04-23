package sk.fmfi.listng.course.application.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
import sk.fmfi.listng.infrastructure.common.BaseController;

import java.util.*;

@RestController
public class ClassController extends BaseController implements ClassApi {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private RoomRepository roomService;
    
    @Autowired 
    private GroupService groupService;
    
    @Autowired
    private ClassRepository repository;
    
    @Override
    public List<ClassDto> getByCourse(Long courseId) throws NotFoundException {
        Optional<Course> course = courseService.getById(courseId);
        
        if (course.isEmpty()) {
            throw new NotFoundException("error.course.not.found");
        }
        
        List<Group> groups = course.get().getGroups().stream().toList();
        Set<ClassDto> classes = new HashSet<>();
        
        for (Group group : groups) {
            classes.addAll(ClassAssembler.toDto(group.getClasses().stream().toList()));
        }
        
        return classes.stream().toList();
    }

    @Override
    public List<ClassDto> getByGroup(Long groupId) {
        List<Class> classes = repository.getClassesByGroupId(groupId);
        return classes.stream()
                .map(ClassAssembler::toDto)
                .toList();
    }

    @Override
    public List<ClassDto> getByRoom(Long roomId) {
        List<Class> classes = repository.getClassesByRoom_Id(roomId);
        return classes.stream()
                .map(ClassAssembler::toDto)
                .toList();
    }

    @Override
    public void create(List<ClassDto> classes) throws NotFoundException {
        for (ClassDto clazz : classes) {
            if (!roomService.existsById(clazz.room.id) || !groupService.groupExists(clazz.groupId)) {
                throw new NotFoundException("error.class.FK.not.found");
            }
        }

        for (ClassDto dto : classes) {
            Class clazz = ClassAssembler.fromDto(dto);
            repository.save(clazz);
        }
    }

    @Override
    public void create(ClassDto clazz) throws NotFoundException {
        if (!roomService.existsById(clazz.room.id) || !groupService.groupExists(clazz.groupId)) {
            throw new NotFoundException("error.class.FK.not.found");
        }

        Class obj = ClassAssembler.fromDto(clazz);
        repository.save(obj);
    }

    @Override
    public void update(List<ClassDto> classes) throws NotFoundException {
        for (ClassDto clazz : classes) {
            if (!repository.existsById(clazz.id)) {
                throw new NotFoundException("error.class.not.found");
            }
        }

        for (ClassDto dto : classes) {
            Class clazz = ClassAssembler.fromDto(dto);
            repository.save(clazz);
        }
    }

    @Override
    public void delete(List<Long> classIds) throws NotFoundException {
        for (Long classId : classIds) {
            if (!repository.existsById(classId)) {
                throw new NotFoundException("error.class.not.found");
            }
        }

        for (Long classId : classIds) {
            repository.deleteById(classId);   
        }
    }

    @Override
    public void delete(Long classId) throws NotFoundException {
        if (!repository.existsById(classId)) {
            throw new NotFoundException("error.class.not.found");
        }
        repository.deleteById(classId);
    }
}
