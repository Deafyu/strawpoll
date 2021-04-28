package back.spring.strawpoll.repository;

import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.entity.VoteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    Optional<VoteEntity> findByUserId(long userId);

    Optional<VoteEntity> findByUserIdAndPollId(long userId, long pollId);

    @Query("SELECT v.user FROM VoteEntity v WHERE v.option.id=:optionId")
    List<UserEntity> findAllOptionVoters(@Param("optionId") long optionId);

    @Query("SELECT v.user FROM VoteEntity v WHERE v.poll.id=:pollId")
    List<UserEntity> findAllPollVoters(@Param("pollId") long pollId);

    @Query("SELECT v FROM VoteEntity v WHERE v.user.id=:userId")
    List<VoteEntity> getAllUserVotes(@Param("userId") long userId);
}
