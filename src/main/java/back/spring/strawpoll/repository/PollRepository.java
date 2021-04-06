package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.PollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<PollEntity,Long> {
}
