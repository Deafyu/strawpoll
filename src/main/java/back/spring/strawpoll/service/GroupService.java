package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.GroupEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.repository.GroupRepository;
import back.spring.strawpoll.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class GroupService {
    GroupRepository groupRepository;
    UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public List<GroupEntity> getAllGroups() {
        List<GroupEntity> groups = new ArrayList<>();
        groups.addAll(groupRepository.findAll());
        return groups;
    }

    public void createGroup(GroupEntity groupEntity) {
        groupRepository.save(groupEntity);
    }

    public void addUserToGroup(long groupId, long userId) {
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(
                () -> new RequestUnavailableException("Group with this id does not exist"));
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new RequestUnavailableException("User with Id " + userId + " doesnt exist"));
        user.addToGroup(group);
        userRepository.save(user);
    }
    public void removeUserFromGroup(long groupId, long userId) {
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(
                () -> new RequestUnavailableException("Group with this id does not exist"));
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new RequestUnavailableException("User with Id " + userId + " doesnt exist"));
        user.removeFromGroup(group);
        userRepository.save(user);
    }

    public List<UserEntity> getAllGroupMembers(long groupId){
        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow(
                () -> new RequestUnavailableException("Group with Id " + groupId + " doesnt exist"));
        return groupEntity.getUsersInGroup();
    }
}
