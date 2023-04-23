package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.sql.Date;

public class PeriodDto implements BaseDto {
    public long id;
    public MultiLangTextDto name;
    public Date start;
    public Date end;
    public boolean active;
}
