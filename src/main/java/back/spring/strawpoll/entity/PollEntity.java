package back.spring.strawpoll.entity;

import back.spring.strawpoll.ut.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class PollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    String question;
    Date pollDateCreation;
    Date pollDateExpiration;
    @NotNull
    Status status;
    @OneToMany
    List<UserEntity> votedBy;
    @OneToMany
    List<OptionEntity> options;
    public void addParticipant(UserEntity user){
        votedBy.add(user);
    }
//    @NotBlank
//    @OneToOne
//    UserEntity createdByUser;

}
