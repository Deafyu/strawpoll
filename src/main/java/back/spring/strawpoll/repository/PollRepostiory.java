package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepostiory extends JpaRepository<Poll,Long> {
}
