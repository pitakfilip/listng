package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.RoomApi;
import sk.fmfi.listng.course.application.assembler.RoomAssembler;
import sk.fmfi.listng.course.application.repository.RoomRepository;
import sk.fmfi.listng.course.domain.Room;
import sk.fmfi.listng.course.dto.RoomDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
public class RoomController implements RoomApi {

    @Autowired
    private RoomRepository repository;
    
    @Override
    public void create(RoomDto room) {
        Room obj = RoomAssembler.fromDto(room);
        repository.save(obj);        
    }

    @Override
    public List<RoomDto> getAll() {
        return repository.findAll().stream()
                .map(RoomAssembler::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getById(long id) {
        Optional<Room> room = repository.findById(id);
        
        if (room.isEmpty()) {
            throw new EntityNotFoundException("error.not.found");
        }
        return RoomAssembler.toDto(room.get());
    }

    @Override
    public void update(RoomDto room) {
        if (!repository.existsById(room.id)) {
            throw new EntityNotFoundException("error.not.found");
        }
        repository.save(RoomAssembler.fromDto(room));
    }

    @Override
    public void deleteById(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("error.not.found");
        }
        repository.deleteById(id);
    }
}
