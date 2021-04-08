package back.spring.strawpoll.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

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
    @OneToMany
    List<OptionEntity> options;
    @Value("0")
    int votes;
//    @NotBlank
//    @OneToOne
//    UserEntity createdByUser;

}
