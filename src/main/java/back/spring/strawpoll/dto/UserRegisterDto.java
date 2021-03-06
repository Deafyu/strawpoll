package back.spring.strawpoll.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterDto {
    String name;
    String password;
    String email;

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
