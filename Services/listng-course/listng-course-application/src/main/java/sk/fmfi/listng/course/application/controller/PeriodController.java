package sk.fmfi.listng.course.application.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.PeriodApi;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.PeriodService;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.domain.course.Period;
import sk.fmfi.listng.infrastructure.common.BaseController;

import javax.naming.CannotProceedException;
import java.io.InvalidObjectException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class PeriodController extends BaseController implements PeriodApi {
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private CourseService courseService;

    @Override
    public void create(PeriodDto dto) throws InvalidObjectException {
        Period period = PeriodAssembler.fromDto(dto);
        
        if (periodService.isValid(period)) {
            periodService.savePeriod(period);
        } else {
            throw new InvalidObjectException("error.period.overlap");
        }
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
    public void update(PeriodDto dto) throws InvalidObjectException {
        Period period = PeriodAssembler.fromDto(dto);
        if (periodService.isValid(period)) {
            periodService.savePeriod(period);
        } else {
            throw new InvalidObjectException("error.period.overlap");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Period> period = periodService.getPeriod(id);
        if (period.isEmpty()){
            throw new NotFoundException("error.period.not.found");
        }
        if (!courseService.getAllInPeriod(id).isEmpty()){
            throw new CannotProceedException("error.period.delete");
        }
        periodService.deletePeriod(id);
    }

}
