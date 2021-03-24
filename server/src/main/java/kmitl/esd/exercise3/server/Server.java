package kmitl.esd.exercise3.server;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Web server
 */
@SpringBootApplication
@ComponentScan(basePackages = {"kmitl.esd.exercise3"})
@EnableJpaRepositories(basePackages={"kmitl.esd.exercise3.repo"})
@EntityScan(basePackages={"kmitl.esd.exercise3.model"})
public class Server {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Server.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8000"));
		app.run(args);
	}

}
