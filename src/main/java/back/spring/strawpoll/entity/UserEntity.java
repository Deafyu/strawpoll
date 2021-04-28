package back.spring.strawpoll.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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
    @NotBlank
    @Column(unique = true)
    String email;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Group",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    List<GroupEntity>groups;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<RoleEntity> roles;
    public void addToGroup(GroupEntity group){
        groups.add(group);
    }
    public void removeFromGroup(GroupEntity group){
        groups.remove(group);
    }
}
