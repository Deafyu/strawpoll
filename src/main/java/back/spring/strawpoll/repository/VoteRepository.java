package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.entity.VoteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    Optional<UserEntity> findByUserId(long userId);
    @Query("SELECT v FROM VoteEntity v WHERE v.option.id=:optionId")
    List<UserEntity> findAllOptionVoters(@Param("optionId")long optionId);
}
