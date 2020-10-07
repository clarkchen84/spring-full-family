package sizhe.chen;

import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sizhe.chen.redis.model.Coffee;
import sizhe.chen.redis.service.CoffeeService;

import java.util.Optional;

/**
 * @ClassName : RedisTemplateApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 10:26
 */
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
@Slf4j
public class RedisTemplateApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;

    @Bean
    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Coffee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer(){
        return clientConfigurationBuilder -> clientConfigurationBuilder.readFrom(ReadFrom.MASTER_PREFERRED);
    }




    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Coffee> c = coffeeService.findOneCoffee("mocha");
        log.info("Coffee {}",c);
        for (int i = 0;i < 5; i++){
            c = coffeeService.findOneCoffee("mocha");

        }
        log.info("Coffee {}",c);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisTemplateApplication.class,args);
    }
}
