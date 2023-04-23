package sk.fmfi.listng.course.application.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.GroupApi;
import sk.fmfi.listng.course.application.assembler.GroupAssembler;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.GroupService;
import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.infrastructure.common.BaseController;

import java.util.List;

@RestController
public class GroupController extends BaseController implements GroupApi {
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private CourseService courseService;
    

    @Override
    public void createGroups(List<GroupDto> groups) throws NotFoundException {
        for (GroupDto groupDto : groups) {
            if (!courseService.courseExists(groupDto.courseId)) {
                throw new NotFoundException("error.course.not.found");
            }
            Group group = GroupAssembler.fromDto(groupDto);
            groupService.save(group);
        }
    }

    @Override
    public void updateGroups(List<GroupDto> groups) throws NotFoundException {
        for (GroupDto groupDto : groups) {
            if (!groupService.groupExists(groupDto.id)){
                throw new NotFoundException("error.group.not.found");
            }
            if (!courseService.courseExists(groupDto.courseId)) {
                throw new NotFoundException("error.course.not.found");
            }
            
            Group group = GroupAssembler.fromDto(groupDto);
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
