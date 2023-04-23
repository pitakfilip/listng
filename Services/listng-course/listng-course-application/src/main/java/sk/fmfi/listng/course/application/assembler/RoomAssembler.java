package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.domain.administration.Room;

import java.util.List;

public class RoomAssembler {
    
    public static RoomDto toDto(Room room) {
        RoomDto dto = new RoomDto();
        dto.id = room.getId();
        dto.name = room.getName();
        dto.capacity = room.getCapacity();
        
        return dto;
    }
    
    public static List<RoomDto> toDto(List<Room> rooms) {
        return rooms.stream()
                .map(RoomAssembler::toDto)
                .toList();
    }
    
    public static Room fromDto(RoomDto dto) {
        Room room = new Room(dto.name, dto.capacity);
        room.setId(dto.id);
        return room;
    }
}
