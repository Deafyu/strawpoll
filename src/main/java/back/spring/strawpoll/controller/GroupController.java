package back.spring.strawpoll.controller;

import back.spring.strawpoll.entity.GroupEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.service.GroupService;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
public class GroupController {
    GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/groups")
    public List<GroupEntity> getAllPolls() {
        return groupService.getAllGroups();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/groups")
            public void createGroup(@RequestBody GroupEntity group) {
        groupService.createGroup(group);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/groups/id={groupId}")
    public void addUserToGroup(@RequestParam long userId,@PathVariable long groupId) {
        groupService.addUserToGroup(groupId, userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/id={groupId}/{userId}")
    public void removeUserFromGroup(@PathVariable long userId,@PathVariable long groupId) {
        groupService.removeUserFromGroup(groupId, userId);
    }
}
