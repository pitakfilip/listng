package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

import java.sql.Date;

public class PeriodDto implements BaseDto {
    public long id;
    public String name;
    public Date start;
}
