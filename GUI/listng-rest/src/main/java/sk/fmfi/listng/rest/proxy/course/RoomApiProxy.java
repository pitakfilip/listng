package sk.fmfi.listng.rest.proxy.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.RoomDto;

import java.util.List;

@FeignClient(name = "listng-course", path = "/course/room")
public interface RoomApiProxy {

    @PostMapping(value = "/new")
    void create(@RequestBody RoomDto room);

    @GetMapping(value = "/all")
    List<RoomDto> getAll();

    @GetMapping(value = "/id")
    RoomDto getById(@RequestParam long id);

    @PostMapping(value = "/update")
    void update(@RequestBody RoomDto room);

    @DeleteMapping(value = "/delete")
    void deleteById(@RequestParam long id);
}
