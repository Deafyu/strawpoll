package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}
