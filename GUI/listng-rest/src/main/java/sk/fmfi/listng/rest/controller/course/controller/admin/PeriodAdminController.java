package sk.fmfi.listng.rest.controller.course.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.proxy.course.PeriodApiProxy;

@RestController
@RequestMapping("/admin/period")
public class PeriodAdminController extends ListController {

    @Autowired
    private PeriodApiProxy periodApi;


    @PostMapping("/page")
    public Response<PageResponse<PeriodDto>> getPeriodsPage(@RequestBody PagingParams paging) {
        PageResponse<PeriodDto> page = periodApi.getPeriodsPage(paging);
        return success(page);
    }
    
    @GetMapping("/{periodId}/delete")
    public Response deletePeriod(@PathVariable Long periodId) {
        periodApi.delete(periodId);
        return success();
    }

    @GetMapping(value = "{periodId}/active/{state}")
    public Response setActive(@PathVariable Long periodId, @PathVariable boolean state) {
        periodApi.setActive(periodId, state);
        return success();
    }
    
    @PostMapping("/save")
    public Response savePeriod(@RequestBody PeriodDto periodDto) {
        periodApi.save(periodDto);
        return success();
    }
}
