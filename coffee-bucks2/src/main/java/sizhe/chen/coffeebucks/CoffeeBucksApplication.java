package sizhe.chen.coffeebucks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sizhe.chen.coffeebucks.bean.CoffeeOrder;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class CoffeeBucksApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeBucksApplication.class,args)
    }

    @Override
    public void run(String... args) throws Exception {
        public boolean updateState(CoffeeOrder order, OrderState state) {
            if (state.compareTo(order.getState()) <= 0) {
                log.warn("Wrong State order: {}, {}", state, order.getState());
                return false;
            }
            order.setState(state);
            orderRepository.save(order);
            log.info("Updated Order: {}", order);
            return true;
        }
    }
}
