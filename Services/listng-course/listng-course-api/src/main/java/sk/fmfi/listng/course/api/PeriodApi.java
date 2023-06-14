package sk.fmfi.listng.course.api;

import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.List;

public interface PeriodApi {

    @PostMapping(value = "/update")
    void save(@RequestBody PeriodDto period);

    @GetMapping(value = "/all")
    List<PeriodDto> getAll();

    @PostMapping(value = "/page")
    PageResponse<PeriodDto> getPeriodsPage(@RequestBody PagingParams page);

    @GetMapping(value = "{periodId}/active/{state}")
    void setActive(@PathVariable Long periodId, @PathVariable boolean state);

    @DeleteMapping(value = "/delete")
    void delete(@RequestParam Long id);
}
