package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;

public class GroupAssembler {

    public static GroupDto toDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.id = group.getId();
        dto.name = MultiLangTextDto.toDto(group.getName());
        dto.courseId = group.getCourseId();
        dto.classes = ClassAssembler.toDto(group.getClasses().stream().toList());
        return dto;
    }

    public static List<GroupDto> toDto(List<Group> groups) {
        return groups.stream()
                .map(GroupAssembler::toDto)
                .toList();
    }
    
    public static List<Group> fromDto(List<GroupDto> dtos) {
        return dtos.stream()
                .map(GroupAssembler::fromDto)
                .toList();
    }
    
    public static Group fromDto(GroupDto dto) {
        Group group = new Group(dto.courseId, MultiLangTextDto.fromDto(dto.name));
        group.setId(dto.id);
        return group;
    }
}
