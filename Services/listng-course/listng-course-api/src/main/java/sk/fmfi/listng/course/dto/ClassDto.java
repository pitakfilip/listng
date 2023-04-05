package sk.fmfi.listng.course.dto;

import sk.fmfi.listng.infrastructure.common.BaseDto;

import java.time.LocalTime;

public class ClassDto implements BaseDto {
    public long id;
    public RoomDto room;
    public GroupDto group;
    public int day;
    public LocalTime time;
    public int duration;
}
