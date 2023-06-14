package sk.fmfi.listng.course.application.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.RoomApi;
import sk.fmfi.listng.course.application.assembler.PeriodAssembler;
import sk.fmfi.listng.course.application.assembler.RoomAssembler;
import sk.fmfi.listng.course.application.repository.RoomRepository;
import sk.fmfi.listng.course.domain.Room;
import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.infrastructure.common.dto.SortParams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
public class RoomController implements RoomApi {

    @Autowired
    private RoomRepository repository;
    
    @Override
    public void save(RoomDto room) {
        repository.save(RoomAssembler.fromDto(room));        
    }

    @Override
    public PageResponse<RoomDto> getRoomsPage(@RequestBody PagingParams params) {
        if (params.sort.isEmpty()) {
            params.sort.add(new SortParams("name"));
        }
        Page<Room> page = repository.findAll(params.compile());
        return new PageResponse<>(page, RoomAssembler.toDto(page.getContent()));
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
    public void deleteById(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("error.not.found");
        }
        repository.deleteById(id);
    }
}
