package sk.fmfi.listng.course.application.assembler;

import sk.fmfi.listng.course.domain.Group;
import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.infrastructure.common.dto.MultiLangTextDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupAssembler {

    public static GroupDto toDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.id = group.getId();
        dto.name = new MultiLangTextDto(group.getName().getSK(), group.getName().getEN());
        dto.courseId = group.getCourseId();
        dto.isDefault = group.isDefaultGroup();
        if (group.getCapacity() != null) {
            dto.capacity = group.getCapacity();
        }
        // TODO
//        dto.classes = ClassAssembler.toDto(group.getClasses()
//                .stream()
//                .toList());
        return dto;
    }

    public static List<GroupDto> toDto(Set<Group> groups) {
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
//        group.setClasses(dto.classes.stream()
//                .map(ClassAssembler::fromDto)
//                .collect(Collectors.toSet()));
        group.setDefaultGroup(dto.isDefault);
        group.setCapacity(dto.capacity);
        return group;
    }
}
