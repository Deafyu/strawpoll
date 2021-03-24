package back.spring.strawpoll.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {

    final long id;

    String name;

    List<Group> groupList;

    boolean isAdmin;
}
