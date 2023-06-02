package sk.fmfi.listng.rest.proxy.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;

import java.util.List;

@FeignClient(name = "listng-course", path = "/course/period")
public interface PeriodApiProxy {

    @PostMapping(value = "/new")
    boolean create(@RequestBody PeriodDto period);

    @GetMapping(value = "/all")
    List<PeriodDto> getAll();

    @PostMapping(value = "/update")
    boolean update(@RequestBody PeriodDto period);

    @DeleteMapping(value = "/delete")
    void delete(@RequestParam Long id);
}
