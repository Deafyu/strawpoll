package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByName(String name);
    public Optional<UserEntity> findByEmail(String email);
}
