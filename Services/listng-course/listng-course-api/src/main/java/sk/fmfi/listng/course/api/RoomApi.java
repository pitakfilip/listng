package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.List;

public interface RoomApi {
    
    @PostMapping(value = "/save")
    void save(@RequestBody RoomDto room);

    @PostMapping(value = "/page")
    PageResponse<RoomDto> getRoomsPage(@RequestBody PagingParams params);

    @GetMapping(value = "/id")
    RoomDto getById(@RequestParam long id);
    
    @DeleteMapping(value = "/delete")
    void deleteById(@RequestParam long id);
}
