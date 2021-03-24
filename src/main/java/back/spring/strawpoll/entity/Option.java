package back.spring.strawpoll.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Option {
    @Id
    long id;
    String name;
    int votes;
    long pollId;
}
