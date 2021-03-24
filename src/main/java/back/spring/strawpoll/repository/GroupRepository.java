package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
