package back.spring.strawpoll.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Poll {
    @Id
    long id;
    //@ElementCollection
    //List<Option> optionList;
    String name;
    Time pollDate;
    Status status;
}
