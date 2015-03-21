import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by gustavokm90 on 3/19/15.
 */
@SpringBootApplication
@ComponentScan(basePackages = "controllers")
public class BarmobRestService {

    public static void main(String[] args) {
        SpringApplication.run(BarmobRestService.class, args);
    }

}
