package sizhe.chen;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sizhe.chen.coffeebucks.bean.Coffee;
import sizhe.chen.coffeebucks.bean.CoffeeOrder;
import sizhe.chen.coffeebucks.repository.CoffeeOrderRepository;
import sizhe.chen.coffeebucks.repository.CoffeeRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class CoffeeApplication  implements CommandLineRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;


    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class,args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initOrder();
    }


    public void initOrder(){
        Coffee espresso =Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),30.0)).build();
        coffeeRepository.save(espresso);

        Coffee latte =Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"),20.0)).build();
        coffeeRepository.save(latte);

        CoffeeOrder coffeeOrderOne = CoffeeOrder.builder().customer("lilei").items(Collections.singletonList(espresso)).state(0).build();
        coffeeOrderRepository.save(coffeeOrderOne);

        CoffeeOrder coffeeOrderTwo = CoffeeOrder.builder().customer("han meimei").items(Arrays.asList(espresso,latte)).state(1).build();
        coffeeOrderRepository.save(coffeeOrderTwo);
        log.info(coffeeOrderOne.toString());
        coffeeOrderRepository.findCoffeeOrdersByCustomer("lilei").forEach(it -> log.error(it.toString()));
        coffeeRepository.findCoffeeByName("espresso").stream().forEach(it -> log.error(it.toString()));
    }
}
