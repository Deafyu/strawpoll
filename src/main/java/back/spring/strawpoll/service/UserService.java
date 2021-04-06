package back.spring.strawpoll.service;

import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.repository.GroupRepository;
import back.spring.strawpoll.repository.OptionRepository;
import back.spring.strawpoll.repository.PollRepository;
import back.spring.strawpoll.repository.UserRepository;
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

    @Autowired
    public UserService(OptionRepository optionRepository, GroupRepository groupRepository, PollRepository pollRepository, UserRepository userRepository) {
        this.optionRepository = optionRepository;
        this.groupRepository = groupRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
    }
    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
    public void createUser(UserEntity user){
        userRepository.save(user);
    }
    public Optional<UserEntity> getUserById(long id){
        return userRepository.findById(id);
    }
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }
}
