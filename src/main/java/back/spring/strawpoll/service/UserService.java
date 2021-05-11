package back.spring.strawpoll.service;

import back.spring.strawpoll.dto.UserRegisterDto;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.entity.VoteEntity;
import back.spring.strawpoll.exception.UserAlreadyExistingException;
import back.spring.strawpoll.repository.*;
import back.spring.strawpoll.ut.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UserService {
    OptionRepository optionRepository;
    GroupRepository groupRepository;
    PollRepository pollRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(OptionRepository optionRepository, GroupRepository groupRepository, PollRepository pollRepository, UserRepository userRepository, VoteRepository voteRepository, RoleRepository roleRepository) {
        this.optionRepository = optionRepository;
        this.groupRepository = groupRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.roleRepository = roleRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public void createUser(UserRegisterDto userRegisterDto) {
        if (!isUserExists(userRegisterDto)) {
            UserEntity user = new UserEntity();
            user.setName(userRegisterDto.getName());
            user.setPassword(getPasswordEncoder().encode(userRegisterDto.getPassword()));
            user.setEmail(userRegisterDto.getEmail());
            userRepository.save(user);
        }
    }

    private boolean isUserExists(UserRegisterDto userRegisterDto) {
        if (getUserByEmail(userRegisterDto.getEmail()).isPresent()) {
//            throw new UserAlreadyExistingException("User with email: " + userRegisterDto.getEmail() + " already exists");
            System.out.println("User with email: " + userRegisterDto.getEmail() + " already exists");
        } else if (getUserByName(userRegisterDto.getName()).isPresent()) {
//            throw new UserAlreadyExistingException("User with name: " + userRegisterDto.getName() + " already exists");
            System.out.println("User with name: " + userRegisterDto.getName() + " already exists");
        } else {
            return false;
        }
        return true;
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

    public Optional<UserEntity> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
