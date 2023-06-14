package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.GroupAssembler;
import sk.fmfi.listng.course.application.repository.GroupRepository;
import sk.fmfi.listng.course.domain.Group;
import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.infrastructure.common.MultiLangText;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void createDefaultGroup(Long courseId) {
        Group group = new Group(courseId, new MultiLangText("Všetci", "All"));
        groupRepository.save(group);
    }
    
    public boolean groupExists(long groupId){
        return groupRepository.existsById(groupId);
    }

    public Long save(GroupDto group) {
        return groupRepository.save(GroupAssembler.fromDto(group)).getId();
    }

    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}
