package sizhe.chen;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sizhe.chen.cache.service.CoffeeOrderService;
import sizhe.chen.cache.service.CoffeeService;

import java.util.Map;

/**
 * @ClassName : JedisApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 21:32
 */
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class JedisApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(JedisApplication.class,args);
    }

    @Autowired
    private  CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedispool;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    @Autowired
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig,@Value("${redis.host}") String host){
        return new JedisPool(jedisPoolConfig, host);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(jedisPoolConfig.toString());

        try(Jedis jedis = jedispool.getResource()){
            coffeeService.findAllCoffee().forEach(
                    c -> {
                        jedis.hset("springbucks-menu",c.getName(),
                                Long.toString(c.getPrice().getAmountMinorLong()));
                    }
            );
            Map<String,String> menu = jedis.hgetAll("springbucks-menu");
            log.info("Menu:{}",menu);
            String price = jedis.hget("springbucks-menu","espresso");
            log.info("espriesso {}", Money.of(CurrencyUnit.of("CNY"),Long.parseLong(price)));

        }
    }
}
