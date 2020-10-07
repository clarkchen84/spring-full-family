package sizhe.chen.redis;

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
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sizhe.chen.redis.converter.BytesToMoneyConverter;
import sizhe.chen.redis.converter.MoneyToBytesConverter;
import sizhe.chen.redis.mode.Coffee;
import sizhe.chen.redis.service.CoffeeService;

import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName : RedisRepostioryApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 20:04
 */
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class RedisRepositoryApplication implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer(){
        return clientConfigurationBuilder -> clientConfigurationBuilder.readFrom(ReadFrom.MASTER_PREFERRED);
    }
    @Bean
    public RedisCustomConversions redisCustomConversions(){
        return new RedisCustomConversions(Arrays.asList(new MoneyToBytesConverter(),new BytesToMoneyConverter()));
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");

        log.info("coffee {} ", c);
        for(int i=0; i < 5; i ++){
            c = coffeeService.findSimpleCoffeeFromCache("mocha");
        }
        log.info("coffee {} ", c);



    }

    public static void main(String[] args) {
        SpringApplication.run(RedisRepositoryApplication.class,args);

    }
}
