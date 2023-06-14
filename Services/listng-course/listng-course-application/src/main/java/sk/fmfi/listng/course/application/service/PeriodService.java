package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.repository.PeriodRepository;
import sk.fmfi.listng.course.domain.Period;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    
    @Autowired
    private PeriodRepository periodRepository;
    
    public void savePeriod(PeriodDto dto){
        if (dto.active) {
            unsetCurrentActive();
        }
        periodRepository.save(PeriodAssembler.fromDto(dto));
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
    
    public PageResponse<PeriodDto> getPeriodsPage(PagingParams params) {
        Page<Period> page = periodRepository.findAll(params.compile());
        return new PageResponse<>(page, PeriodAssembler.toDto(page.getContent()));
    }
    
    private void unsetCurrentActive() {
        Period period = periodRepository.findFirstByActiveIsTrue();
        if (period != null) {
            period.setActive(false);
            periodRepository.save(period);    
        }
    }
    
    public void setActive(Long periodId, boolean state) {
        Period period = periodRepository.getReferenceById(periodId);
        if (state) {
            unsetCurrentActive();
        }
        period.setActive(state);
        periodRepository.save(period);
    }
}
