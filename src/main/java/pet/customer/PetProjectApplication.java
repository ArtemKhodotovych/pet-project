package pet.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * Main spring boot 2 start class
 */
@SpringBootApplication
@EnableSwagger2
public class PetProjectApplication {

    /**
     * the main start method
     * @param args - comand line args
     */
    public static void main(String[] args) {
        SpringApplication.run(PetProjectApplication.class, args);
    }

    /**
     * Swagger UI rest api selector
     * @see PetProjectApplication#api()
     * @return swagger configuration class
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

}

