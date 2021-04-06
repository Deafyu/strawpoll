package back.spring.strawpoll.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    String name;
    @NotBlank
    String password;
    @NotNull
    boolean isAdmin;
    @ManyToMany
    List<GroupEntity>groups;
}
