package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
