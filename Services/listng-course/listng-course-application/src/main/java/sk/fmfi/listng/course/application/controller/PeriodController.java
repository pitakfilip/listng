package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.PeriodApi;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.service.CourseService;
import sk.fmfi.listng.course.application.service.PeriodService;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.PeriodDto;

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
    public boolean create(PeriodDto periodDto) {
        if (!periodService.isValid(periodDto)) {
            return false;
        }

        periodService.savePeriod(periodDto);
        return true;
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
    public boolean update(PeriodDto periodDto) {
        if (!periodService.isValid(periodDto)) {
            return false;
        }
        periodService.savePeriod(periodDto);
        return true;
    }

    @Override
    public void delete(Long id) {
        Optional<Period> period = periodService.getPeriod(id);
        if (period.isEmpty()){
            throw new EntityNotFoundException("error.period.not.found");
        }
        if (!courseService.getAllInPeriod(id).isEmpty()){
//            throw new CannotProceedException("error.period.delete");
        }
        periodService.deletePeriod(id);
    }

}
