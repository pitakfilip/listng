package sk.fmfi.listng.rest.proxy.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.List;

@FeignClient(name = "listng-course", path = "/course/room")
public interface RoomApiProxy {

    @PostMapping(value = "/save")
    void save(@RequestBody RoomDto room);

    @PostMapping(value = "/page")
    PageResponse<RoomDto> getRoomsPage(@RequestBody PagingParams page);

    @GetMapping(value = "/id")
    RoomDto getById(@RequestParam long id);

    @DeleteMapping(value = "/delete")
    void deleteById(@RequestParam long id);
}
