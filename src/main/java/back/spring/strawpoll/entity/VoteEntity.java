package back.spring.strawpoll.ut;

import back.spring.strawpoll.entity.OptionEntity;
import back.spring.strawpoll.entity.PollEntity;
import back.spring.strawpoll.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class VoteEntity {
    @Id
    long voteId;
    @OneToOne
    OptionEntity option;
    @OneToOne
    PollEntity poll;
    @OneToOne
    UserEntity user;
    @NotBlank
    Date date;
}
