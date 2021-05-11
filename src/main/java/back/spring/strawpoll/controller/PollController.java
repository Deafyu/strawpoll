package back.spring.strawpoll.controller;

import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.service.PollService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true)
@RestController
public class PollController {
    PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @RequestMapping(value = "/polls")
    public List<PollEntity> getAllPolls() {
        return pollService.getAllPolls();
    }

    @RequestMapping("/polls/id={pollId}")
    public Optional<PollEntity> getPollById(@PathVariable long pollId) {
        return pollService.getSinglePollById(pollId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/polls")
    public void addPoll(@RequestBody PollEntity poll) {
        pollService.createPoll(poll);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/polls/id={pollId}")
    public void deletePoll(@PathVariable long pollId) {
        pollService.deletePoll(pollId);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/polls/id={pollId}/vote")
    public String submitVote(@RequestParam long optionId, @RequestParam long userId, @PathVariable long pollId) {
        return pollService.submitVote(optionId, userId, pollId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/polls/id={pollId}/vote/{optionId}")
    public List<UserEntity> displayOptionVoters(@PathVariable long pollId, @PathVariable long optionId) {
        return pollService.displayOptionVoters(optionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/polls/id={pollId}/vote")
    public List<UserEntity> displayPollVoters(@PathVariable long pollId) {
        return pollService.displayPollVoters(pollId);
    }

}
