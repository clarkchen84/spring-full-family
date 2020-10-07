package sizhe.chen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sizhe.chen.cache.service.CoffeeService;

import java.util.stream.IntStream;

/**
 * @ClassName : CacheApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 23:03
 */
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class CacheApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        IntStream.range(0,10).forEach(i ->{ log.info("reading from cache.");
            coffeeService.findAllCoffee();
        });
        Thread.sleep(5000);
        log.info("reading after refresh.");
        coffeeService.findAllCoffee().forEach(c -> log.info("coffee"));



    }

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class,args);
    }
}
