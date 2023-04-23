package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;

import java.time.LocalTime;

public class ClassDto implements BaseDto {
    public long id;
    public RoomDto room;
    public long groupId;
    public int day;
    public LocalTime time;
    public int duration;
}
