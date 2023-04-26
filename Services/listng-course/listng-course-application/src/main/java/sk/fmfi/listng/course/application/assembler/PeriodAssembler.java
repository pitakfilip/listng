package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.domain.course.Period;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;
import java.util.stream.Collectors;

public class PeriodAssembler {
    
    public static PeriodDto toDto(Period period) {
        PeriodDto dto = new PeriodDto();
        dto.id = period.getId();
        System.out.println("PERIOD S ID: " + dto.id);
        dto.name = new MultiLangTextDto(period.getName());
        dto.start = period.getStart();
        dto.end = period.getEnd();
        dto.active = period.isActive();
        return dto;
    }
    
    public static List<PeriodDto> toDto(List<Period> periods) {
        return periods.stream()
                .map(PeriodAssembler::toDto)
                .collect(Collectors.toList());
    }
    
    public static Period fromDto(PeriodDto dto) {
        Period period = new Period();
        period.setId(dto.id);
        period.setName(MultiLangTextDto.fromDto(dto.name));
        period.setStart(dto.start);
        period.setEnd(dto.end);
        
        return period;
    }
}
