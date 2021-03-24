package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
