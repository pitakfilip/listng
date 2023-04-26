package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.RoomDto;

import java.util.List;

public interface RoomApi {
    
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
