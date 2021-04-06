package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
