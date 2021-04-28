package back.spring.strawpoll;

import back.spring.strawpoll.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StrawpollApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrawpollApplication.class, args);
	}

}
