package sk.fmfi.listng.course.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.course.application.assembler.GroupAssembler;
import sk.fmfi.listng.course.application.repository.GroupRepository;
import sk.fmfi.listng.course.dto.GroupDto;
import sk.fmfi.listng.domain.administration.Group;
import sk.fmfi.listng.domain.administration.MultiLangText;
import sk.fmfi.listng.domain.course.Course;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void createDefaultGroup(Course course) {
        Group group = new Group(course.getId(), new MultiLangText("Všetci", "All"));
        groupRepository.save(group);
    }
    
    public boolean groupExists(long groupId){
        return groupRepository.existsById(groupId);
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}
