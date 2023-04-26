package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import java.util.List;

public interface PeriodApi {

    @PostMapping(value = "/new")
    boolean create(@RequestBody PeriodDto period);
    
    @GetMapping(value = "/all")
    List<PeriodDto> getAll();
    
    @PostMapping(value = "/update")
    boolean update(@RequestBody PeriodDto period);
    
    @DeleteMapping(value = "/delete")
    void delete(@RequestParam Long id);
}
