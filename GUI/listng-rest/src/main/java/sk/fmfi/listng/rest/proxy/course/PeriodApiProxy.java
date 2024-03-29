package sk.fmfi.listng.rest.proxy.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.List;

@FeignClient(name = "listng-course", path = "/course/period")
public interface PeriodApiProxy {

    @PostMapping(value = "/update")
    void save(@RequestBody PeriodDto period);
    
    @GetMapping(value = "/all")
    List<PeriodDto> getAll();

    @PostMapping(value = "/page")
    PageResponse<PeriodDto> getPeriodsPage(@RequestBody PagingParams page);

    @GetMapping(value = "{periodId}/active/{state}")
    void setActive(@PathVariable Long periodId, @PathVariable boolean state);

    @DeleteMapping(value = "/delete")
    Response delete(@RequestParam Long id);
}
