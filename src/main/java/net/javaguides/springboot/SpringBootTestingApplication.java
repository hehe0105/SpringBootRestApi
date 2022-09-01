package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users API", version = "1.0", description = "Users Information"))
public class SpringBootTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestingApplication.class, args);
	}
}
