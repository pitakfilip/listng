package sk.fmfi.listng.course.api;


import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;

import java.io.InvalidObjectException;
import java.util.List;

public interface PeriodApi {

    @PostMapping(value = "/period/new")
    void create(@RequestBody PeriodDto period) throws InvalidObjectException;
    
    @GetMapping(value = "/period/all")
    List<PeriodDto> getAll();
    
    @PostMapping(value = "/period/update")
    void update(@RequestBody PeriodDto period) throws InvalidObjectException;
    
    @DeleteMapping(value = "/period/delete")
    void delete(@RequestParam Long id) throws Exception;
}
