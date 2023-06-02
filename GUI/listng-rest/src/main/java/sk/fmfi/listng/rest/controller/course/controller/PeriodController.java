package sk.fmfi.listng.rest.controller.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.proxy.course.PeriodApiProxy;

import java.util.List;

@RestController
@RequestMapping("/period")
public class PeriodController extends ListController {
    
    @Autowired
    private PeriodApiProxy periodApi;
    
    @GetMapping("/periods")
    public Response<List<PeriodDto>> getPeriods() {
        return success(periodApi.getAll());
    }
    
}
