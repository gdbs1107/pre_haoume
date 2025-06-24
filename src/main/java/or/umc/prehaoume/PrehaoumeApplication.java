package or.umc.prehaoume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PrehaoumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrehaoumeApplication.class, args);
    }

}