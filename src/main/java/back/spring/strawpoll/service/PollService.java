package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.exception.RequestUnavailableException;
import back.spring.strawpoll.repository.*;
import back.spring.strawpoll.entity.VoteEntity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import back.spring.strawpoll.response.*;


import java.util.*;

import static back.spring.strawpoll.ut.DateTimeUtil.getCurrentDate;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class PollService {
    Response response = new Response();
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


    public String submitVote(long optionId, long userId, long pollId) {
        Optional<PollEntity> optionalPollEntity = pollRepository.findById(pollId);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        Optional<VoteEntity> optionalVoteEntity = voteRepository.findByUserIdAndPollId(userId, pollId);
        Optional<OptionEntity> optionalOptionEntity;

        if (optionalVoteEntity.isPresent()) response.setBody("cccc");
        if (optionalUserEntity.isEmpty()) response.setBody("bbbb");
        if (optionalPollEntity.isEmpty()) response.setBody("xd");

        if (response.getBody().isEmpty()) {
            PollEntity pollEntity = optionalPollEntity.get();
            optionalOptionEntity = pollEntity.getOptions()
                    .stream()
                    .filter(o -> o.getId() == optionId)
                    .findFirst();

            if (optionalOptionEntity.isEmpty()) response.setBody("fadsf");
            if (isPollExpired(pollEntity.getPollDateExpiration())) response.setBody("eeee");
            if (!response.getBody().isEmpty()) return "";

            OptionEntity optionEntity = optionalOptionEntity.get();
            UserEntity userEntity = optionalUserEntity.get();
            VoteEntity voteEntity = createVoteEntity(userEntity, optionEntity, pollEntity);
            voteRepository.save(voteEntity);
            optionRepository.save(optionEntity);
            pollRepository.save(pollEntity);
        }
        return response.getBody();
    }

    private VoteEntity createVoteEntity(UserEntity userEntity, OptionEntity optionEntity, PollEntity pollEntity) {
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setUser(userEntity);
        voteEntity.setOption(optionEntity);
        voteEntity.setPoll(pollEntity);
        voteEntity.setDate(getCurrentDate());
        return voteEntity;
    }

    private boolean isPollExpired(Date date) {
        return date != null && getCurrentDate().after(date);
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
