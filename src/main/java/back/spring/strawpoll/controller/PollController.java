package back.spring.strawpoll.controller;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.service.PollService;
import back.spring.strawpoll.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
public class PollController {
    PollService pollService;
    UserService userService;

    @Autowired
    public PollController(PollService pollService, UserService userService) {
        this.pollService = pollService;
        this.userService = userService;
    }

    @RequestMapping(value = "/polls")
    public List<PollEntity> getAllPolls() {
        return pollService.getAllPolls();
    }

    @RequestMapping("/polls/id={id}")
    public PollEntity getPoll(@PathVariable long id) {
        return pollService.getSinglePollById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/polls")
    public void addPoll(@RequestBody PollEntity poll) {
        pollService.createPoll(poll);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/polls/id={id}")
    public void deletePoll(@PathVariable long id) {
        pollService.deletePoll(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/polls/id={id}/vote")
    public void sumbitVote(long optionId, long userId, long pollId){
        pollService.submitVote(optionId,userId,pollId);
    }


}
