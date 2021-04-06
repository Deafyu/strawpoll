package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
