package site.metacoding.project_sh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectFrontendApplication.class, args);
	}

}
