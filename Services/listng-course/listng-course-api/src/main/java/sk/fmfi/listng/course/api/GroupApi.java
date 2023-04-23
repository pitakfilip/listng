package sk.fmfi.listng.course.api;


import javassist.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sk.fmfi.listng.course.dto.GroupDto;

import java.util.List;

/**
 * Api for manipulating existing groups. Provides only Create, Update and Delete operations, 
 * as reading groups is provided by CourseApi since the only need of viewing groups is connected
 * by viewing a Course.
 * @see CourseApi
 */
public interface GroupApi {
    
    @PostMapping(value = "/group/new")
    void createGroups(@RequestBody List<GroupDto> groups) throws NotFoundException;
    
    @PostMapping(value = "/groups/update")
    void updateGroups(@RequestBody List<GroupDto> groups) throws NotFoundException;
    
    @PostMapping(value = "/groups/delete")
    void deleteGroups(@RequestParam List<GroupDto> groups);
}
