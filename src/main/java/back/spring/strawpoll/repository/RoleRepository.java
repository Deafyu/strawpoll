package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.RoleEntity;
import back.spring.strawpoll.ut.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public RoleEntity findByRole(Role role);
}
