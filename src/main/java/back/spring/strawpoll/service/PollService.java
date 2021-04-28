package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.repository.*;
import back.spring.strawpoll.entity.VoteEntity;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static back.spring.strawpoll.ut.DateTimeUtil.getCurrentDate;

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
        return pollRepository.findAll();
    }

    public Optional<PollEntity> getSinglePollById(long id) {
        return pollRepository.findById(id);
    }

    public void createPoll(PollEntity pollEntity) {
        pollRepository.save(pollEntity);
    }

    public void deletePoll(long id) {
        pollRepository.deleteById(id);
    }

    public void submitVote(long optionId, long userId, long pollId) {
        VoteEntity vote = new VoteEntity();
        Date currentDate = getCurrentDate();
        Optional<PollEntity> poll = pollRepository.findById(pollId);
        Optional<UserEntity> user = userRepository.findById(userId);
        OptionEntity optionEntity;
        UserEntity userEntity;
        PollEntity pollEntity;
        if (poll.isPresent() && user.isPresent() ) {
            pollEntity= poll.get();
            userEntity= user.get();
        }else{
            throw new RequestUnavailableException("This type of vote is impossible");
        }
        if (voteRepository.findByUserIdAndPollId(userId, pollId).isPresent()) {
            throw new RequestUnavailableException("You have already voted in this poll");
        }
        Optional<OptionEntity> option = pollEntity.getOptions().stream().filter(o -> o.getId() == optionId).findFirst();
        if (option.isPresent()) {
            optionEntity = option.get();
        } else {
            throw new RequestUnavailableException("Option with Id " + optionId + " doest not exist");
        }
        if (pollEntity.getPollDateExpiration() != null && currentDate.after(pollEntity.getPollDateExpiration())) {
            throw new RequestUnavailableException("This poll has expired");
        }
        vote.setUser(userEntity);
        vote.setOption(optionEntity);
        vote.setPoll(pollEntity);
        vote.setDate(currentDate);
        addVoteCount(optionEntity, pollEntity);
        voteRepository.save(vote);
        optionRepository.save(optionEntity);
        pollRepository.save(pollEntity);
    }

    private void addVoteCount(OptionEntity option, PollEntity poll) {
        option.setVotes(option.getVotes() + 1);
        poll.setVotes(poll.getVotes() + 1);
    }

    public List<UserEntity> displayOptionVoters(long optionId) {
        return voteRepository.findAllOptionVoters(optionId);
    }

    public List<UserEntity> displayPollVoters(long pollId) {
        return voteRepository.findAllPollVoters(pollId);
    }
}
