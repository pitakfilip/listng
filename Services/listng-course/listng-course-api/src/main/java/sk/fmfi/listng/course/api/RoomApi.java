package sk.fmfi.listng.course.api;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.RoomDto;

import java.util.List;

public interface RoomApi {
    
    @PostMapping(value = "/room/new")
    void create(@RequestBody RoomDto room);
    
    @GetMapping(value = "/rooms")
    List<RoomDto> getAll();
    
    @GetMapping(value = "/room/id")
    RoomDto getById(@RequestParam long id) throws NotFoundException;
    
    @PostMapping(value = "/room/update")
    void update(@RequestBody RoomDto room) throws NotFoundException;
    
    @DeleteMapping(value = "/room/delete")
    void deleteById(@RequestParam long id) throws NotFoundException;
}
