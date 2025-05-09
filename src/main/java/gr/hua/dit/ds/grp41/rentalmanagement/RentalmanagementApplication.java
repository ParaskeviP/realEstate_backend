package gr.hua.dit.ds.grp41.rentalmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "My API", version = "v1", description = "API documentation for my app")
)
public class RentalmanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalmanagementApplication.class, args);
	}
}