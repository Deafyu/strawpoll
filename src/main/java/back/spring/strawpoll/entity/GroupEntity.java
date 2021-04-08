package back.spring.strawpoll.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    String groupName;
    @ManyToMany
    List<PollEntity>polls;
    @NotBlank
    int maxUsers;
    @ManyToMany(mappedBy = "groups")
    List<UserEntity> usersInGroup;
}
