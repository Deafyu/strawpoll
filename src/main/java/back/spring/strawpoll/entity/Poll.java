package back.spring.strawpoll.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Poll {

    long id;
    List<Option> optionList;
    String name;
    Time pollDate;
    Status status;
}
