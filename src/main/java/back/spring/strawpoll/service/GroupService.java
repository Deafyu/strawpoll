package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.GroupEntity;
import back.spring.strawpoll.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> getAllGroups(){
        List<GroupEntity>groups = new ArrayList<>();
        groups.addAll(groupRepository.findAll());
        return groups;
    }
}
