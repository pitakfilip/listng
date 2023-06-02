package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.repository.PeriodRepository;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.PeriodDto;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    
    @Autowired
    private PeriodRepository periodRepository;
    
    public void savePeriod(PeriodDto period){
        periodRepository.save(PeriodAssembler.fromDto(period));
    }
    
    public List<Period> getAllPeriods() {
        return periodRepository.findAllByOrderByStartDesc();
    }
    
    public Optional<Period> getPeriod(Long id) {
        return periodRepository.findById(id);
    }
    
    public boolean exists(Long periodId) {
        return periodRepository.existsById(periodId);
    }
    
    public Long getActivePeriodId() {
        List<Period> periods = getAllPeriods();
        List<PeriodDto> periodDtos = PeriodAssembler.toDto(periods);
        
        for (PeriodDto period : periodDtos) {
            if (period.active){
                return period.id;
            }
        }
        
        return null;
    }
    
    public void deletePeriod(Long id){
        periodRepository.deleteById(id);
    }

    public boolean isValid(PeriodDto dto) {
        Period period = PeriodAssembler.fromDto(dto);
        List<Period> periods = getAllPeriods();
        for (Period existingPeriod : periods) {
            if (existingPeriod.overlaps(period)) {
                return false;
            }
        }
        return true;
    }
}
