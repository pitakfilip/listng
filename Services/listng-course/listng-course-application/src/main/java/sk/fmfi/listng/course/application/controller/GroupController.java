package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.GroupApi;
import sk.fmfi.listng.course.application.assembler.GroupAssembler;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.GroupService;
import sk.fmfi.listng.course.domain.Group;
import sk.fmfi.listng.course.dto.GroupDto;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController implements GroupApi {
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private CourseService courseService;
    

    @Override
    public void createGroups(List<GroupDto> groups) {
        for (GroupDto group : groups) {
            if (!courseService.courseExists(group.courseId)) {
                throw new EntityNotFoundException("error.course.not.found");
            }
            groupService.save(group);
        }
    }

    @Override
    public void updateGroups(List<GroupDto> groups) {
        for (GroupDto group : groups) {
            if (!groupService.groupExists(group.id)){
                throw new EntityNotFoundException("error.group.not.found");
            }
            if (!courseService.courseExists(group.courseId)) {
                throw new EntityNotFoundException("error.course.not.found");
            }
            
            groupService.save(group);
        }
    }

    @Override
    public void deleteGroups(List<GroupDto> groups) {
        for (GroupDto group : groups) {
            groupService.deleteById(group.id);
        }
    }
}
