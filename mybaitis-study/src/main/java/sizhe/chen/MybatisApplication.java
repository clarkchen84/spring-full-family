package sizhe.chen;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sizhe.chen.data.mybatis.bean.Coffee;
import sizhe.chen.data.mybatis.mapper.CoffeeMapper;

@SpringBootApplication
@MapperScan("sizhe.chen.data.mybatis.mapper")
@Slf4j
public class MybatisApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class,args);
    }
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Override
    public void run(String... args) throws Exception {
        Coffee c = Coffee.builder().name("espresso").
                price(Money.of(CurrencyUnit.of("CNY"),20.0)).build();
        int count = coffeeMapper.save(c);
        log.info("count=======" + count);
        log.info("count=======" + c.toString());
        Coffee coffee = coffeeMapper.findById(c.getId());
        log.info("coffee======" + coffee.toString());
       // coffeeMapper.findById()
    }
}
