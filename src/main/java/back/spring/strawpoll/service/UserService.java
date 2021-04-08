package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.entity.VoteEntity;
import back.spring.strawpoll.repository.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true)
@Service
public class UserService {
    OptionRepository optionRepository;
    GroupRepository groupRepository;
    PollRepository pollRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    @Autowired
    public UserService(OptionRepository optionRepository, GroupRepository groupRepository, PollRepository pollRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.optionRepository = optionRepository;
        this.groupRepository = groupRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    public List<VoteEntity> getAllUserVotes(long userId) {
        return voteRepository.getAllUserVotes(userId);
    }
}
