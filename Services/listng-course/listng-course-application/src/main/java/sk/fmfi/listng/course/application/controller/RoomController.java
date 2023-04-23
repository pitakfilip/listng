package sk.fmfi.listng.course.application.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.RoomApi;
import sk.fmfi.listng.course.application.assembler.RoomAssembler;
import sk.fmfi.listng.course.application.repository.RoomRepository;
import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.domain.administration.Room;
import sk.fmfi.listng.infrastructure.common.BaseController;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController extends BaseController implements RoomApi {

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
                .toList();
    }

    @Override
    public RoomDto getById(long id) throws NotFoundException {
        Optional<Room> room = repository.findById(id);
        
        if (room.isEmpty()) {
            throw new NotFoundException("error.not.found");
        }
        return RoomAssembler.toDto(room.get());
    }

    @Override
    public void update(RoomDto room) throws NotFoundException {
        if (!repository.existsById(room.id)) {
            throw new NotFoundException("error.not.found");
        }
        repository.save(RoomAssembler.fromDto(room));
    }

    @Override
    public void deleteById(long id) throws NotFoundException {
        if (!repository.existsById(id)) {
            throw new NotFoundException("error.not.found");
        }
        repository.deleteById(id);
    }
}
