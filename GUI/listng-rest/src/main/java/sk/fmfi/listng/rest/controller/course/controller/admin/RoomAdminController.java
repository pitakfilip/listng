package sk.fmfi.listng.rest.controller.course.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.fmfi.listng.course.dto.PeriodDto;
import sk.fmfi.listng.course.dto.RoomDto;
import sk.fmfi.listng.infrastructure.common.Response;
import sk.fmfi.listng.infrastructure.common.dto.PageResponse;
import sk.fmfi.listng.infrastructure.common.dto.PagingParams;
import sk.fmfi.listng.rest.common.ListController;
import sk.fmfi.listng.rest.proxy.course.ClassApiProxy;
import sk.fmfi.listng.rest.proxy.course.RoomApiProxy;

@RestController
@RequestMapping("/admin/room")
public class RoomAdminController extends ListController {
    
    @Autowired
    private RoomApiProxy roomApi;

    @PostMapping("/page")
    public Response<PageResponse<RoomDto>> getRoomsPage(@RequestBody PagingParams paging) {
        PageResponse<RoomDto> page = roomApi.getRoomsPage(paging);
        return success(page);
    }
    
    @PostMapping("/save")
    public Response saveRoom(@RequestBody RoomDto roomDto) {
        roomApi.save(roomDto);
        return success();
    }
    
    @GetMapping("/{roomId}/delete")
    public Response deleteRoom(@PathVariable Long roomId) {
        roomApi.deleteById(roomId);
        return success();
    }
}
