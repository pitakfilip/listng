package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.dto.ClassDto;
import sk.fmfi.listng.course.domain.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassAssembler {

    public static ClassDto toDto(Class clazz) {
        ClassDto dto = new ClassDto();
        dto.id = clazz.getId();
        dto.room = RoomAssembler.toDto(clazz.getRoom());
        dto.groupId = clazz.getGroupId();
        dto.day = clazz.getDay();
        dto.time = clazz.getTime();
        dto.duration = clazz.getDuration();
        return dto;
    }

    public static List<ClassDto> toDto(List<Class> classes) {
        List<ClassDto> dtos = new ArrayList<>();
        
        for (Class clazz : classes) {
            dtos.add(toDto(clazz));
        }
        
        return dtos;
//        return classes.stream()
//                .map(ClassAssembler::toDto)
//                .toList();
    }
    
    public static Class fromDto(ClassDto dto) {
        Class clazz = new Class(RoomAssembler.fromDto(dto.room), dto.groupId, dto.day, dto.time, dto.duration);
        clazz.setId(dto.id);
        return clazz;        
    }
}
