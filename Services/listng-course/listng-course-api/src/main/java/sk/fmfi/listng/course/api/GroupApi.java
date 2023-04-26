package sk.fmfi.listng.course.api;

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
    
    @PostMapping(value = "/new")
    void createGroups(@RequestBody List<GroupDto> groups);
    
    @PostMapping(value = "/update")
    void updateGroups(@RequestBody List<GroupDto> groups);
    
    @PostMapping(value = "/delete")
    void deleteGroups(@RequestParam List<GroupDto> groups);
}
