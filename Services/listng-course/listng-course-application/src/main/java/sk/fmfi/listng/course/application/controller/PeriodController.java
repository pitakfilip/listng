package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.PeriodApi;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.PeriodService;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.infrastructure.common.dto.SortParams;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/period")
public class PeriodController implements PeriodApi {
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private CourseService courseService;


    @Override
    public void save(PeriodDto periodDto) {
        periodService.savePeriod(periodDto);
    }
    
    @Override
    public List<PeriodDto> getAll() {
        List<Period> periods = periodService.getAllPeriods();

        if (periods == null || periods.isEmpty()){
            return Collections.emptyList();
        }
        return PeriodAssembler.toDto(periods);
    }
    
    @Override
    public PageResponse<PeriodDto> getPeriodsPage(PagingParams params) {
        if (params.sort.isEmpty()) {
            params.sort.add(new SortParams("start"));
        }

        return periodService.getPeriodsPage(params);
    }

    @Override
    public void setActive(@PathVariable Long periodId, @PathVariable boolean state) {
        periodService.setActive(periodId, state);
    }

    @Override
    public Response delete(Long id) {
        Optional<Period> period = periodService.getPeriod(id);
        if (period.isEmpty()){
            throw new EntityNotFoundException("error.period.not.found");
        }
        Response response = new Response();
        if (!courseService.getAllInPeriod(id).isEmpty()){
//            response = response.withErrorMessage("error.period.delete.courses");
//            return response;
        }
        periodService.deletePeriod(id);
        return response;
    }
}
