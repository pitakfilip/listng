package sk.fmfi.listng.rest.proxy.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sk.fmfi.listng.course.dto.GroupDto;

import java.util.List;

@FeignClient(name = "listng-course", path = "/course/group")
public interface GroupApiProxy {

    @PostMapping(value = "/new")
    void createGroups(@RequestBody List<GroupDto> groups);

    @PostMapping(value = "/update")
    void updateGroups(@RequestBody List<GroupDto> groups);

    @PostMapping(value = "/delete")
    void deleteGroups(@RequestParam List<GroupDto> groups);
}
