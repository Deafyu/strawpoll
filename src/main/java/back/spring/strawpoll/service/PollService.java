package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.repository.GroupRepository;
import back.spring.strawpoll.repository.OptionRepository;
import back.spring.strawpoll.repository.PollRepository;
import back.spring.strawpoll.repository.UserRepository;
import back.spring.strawpoll.ut.Status;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true)
@Service
public class PollService {
    OptionRepository optionRepository;
    UserRepository userRepository;
    GroupRepository groupRepository;
    PollRepository pollRepository;

    @Autowired
    public PollService(OptionRepository optionRepository, UserRepository userRepository, GroupRepository groupRepository, PollRepository pollRepository) {
        this.optionRepository = optionRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.pollRepository = pollRepository;
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
        PollEntity poll = pollRepository.findById(pollId).orElseThrow(
                () -> new RequestUnavailableException("Poll with Id " + pollId + " doesnt exist"));
        OptionEntity option = optionRepository.findById(optionId).orElseThrow(
                () -> new RequestUnavailableException("Option with Id " + optionId + " doesnt exist"));
        if (poll.getPollDateExpiration() != null && now.after(poll.getPollDateExpiration())) {
            throw new RequestUnavailableException("This poll expired");
        }
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new RequestUnavailableException("User with Id " + userId + " doesnt exist"));
        if (poll.getVotedBy().stream().anyMatch(item -> item.getId() == userId)) {
            throw new RequestUnavailableException("You have already voted in this poll");
        }
        option.addParticipant(user);
        poll.addParticipant(user);
        option.setVotes(option.getVotes() + 1);
        optionRepository.save(option);
        pollRepository.save(poll);
    }

//    public Map<OptionEntity, List<UserEntity>> displayVoters(long pollId) {
//        PollEntity poll = pollRepository.findById(pollId).orElseThrow(
//                () -> new RequestUnavailableException("Poll with Id " + pollId + " doesnt exist"));
//        List<OptionEntity> options = poll.getOptions();
//        Map<OptionEntity, List<UserEntity>> optionVotes = new HashMap<>();
//        for (OptionEntity option : options) {
//            optionVotes.put(option, option.getVotedBy());
//        }
//        return optionVotes;
//    }
}
