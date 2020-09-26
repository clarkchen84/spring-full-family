package sizhe.chen.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : SpingFirstApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-25 23:20
 */
@SpringBootApplication
@RestController
public class SpringFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFirstApplication.class,args);
    }

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello";
    }
}
