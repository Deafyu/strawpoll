package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.repository.*;
import back.spring.strawpoll.entity.VoteEntity;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@FieldDefaults(makeFinal = true)
@Service
public class PollService {
    OptionRepository optionRepository;
    UserRepository userRepository;
    GroupRepository groupRepository;
    PollRepository pollRepository;
    VoteRepository voteRepository;

    @Autowired
    public PollService(OptionRepository optionRepository, UserRepository userRepository, GroupRepository groupRepository, PollRepository pollRepository, VoteRepository voteRepository) {
        this.optionRepository = optionRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    public List<PollEntity> getAllPolls() {
        return new ArrayList<>(pollRepository.findAll());
    }

    public PollEntity getSinglePollById(long id) {
        return pollRepository.findById(id).orElse(null);
    }

    public void createPoll(PollEntity pollEntity) {
        pollRepository.save(pollEntity);
    }

    public void deletePoll(long id) {
        pollRepository.deleteById(id);
    }

    public void submitVote(long optionId, long userId, long pollId) {
        Date now = new Date();
        VoteEntity vote = new VoteEntity();
        PollEntity poll = pollRepository.findById(pollId).orElseThrow(
                () -> new RequestUnavailableException("Poll with Id " + pollId + " doesnt exist"));
        OptionEntity option = optionRepository.findById(optionId).orElseThrow(
                () -> new RequestUnavailableException("Option with Id " + optionId + " doesnt exist"));
        if (poll.getPollDateExpiration() != null && now.after(poll.getPollDateExpiration())) {
            throw new RequestUnavailableException("This poll expired");
        }
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new RequestUnavailableException("User with Id " + userId + " doesnt exist"));
        if (voteRepository.findByUserId(userId).isPresent()){
            throw new RequestUnavailableException("You have already voted in this poll");
        }
        vote.setUser(user);
        vote.setOption(option);
        vote.setPoll(poll);
        vote.setDate(now);
        addVoteCount(option, poll);
        voteRepository.save(vote);
        optionRepository.save(option);
        pollRepository.save(poll);
    }

    private void addVoteCount(OptionEntity option, PollEntity poll) {
        option.setVotes(option.getVotes() + 1);
        poll.setVotes(poll.getVotes()+1);
    }

    public List<UserEntity> displayVoters(long optionId) {
        return voteRepository.findAllOptionVoters(optionId);
    }
}
