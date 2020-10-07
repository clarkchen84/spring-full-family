package sizhe.chen;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sizhe.chen.mogodb.bean.Coffee;
import sizhe.chen.mogodb.converter.MoneyReadConverter;
import sizhe.chen.mogodb.repository.CoffeeRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : MongoDBApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 11:08
 */
@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class MongoDBApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CoffeeRepository coffeeReposiotry;

    public static void main(String[] args) {
        SpringApplication.run(MongoDBApplication.class,args);
    }
    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        queryByTemplate();

        queryByRepository();
    }

    public void queryByTemplate() throws InterruptedException {
        mongoTemplate.remove(Query.query(Criteria.where("")), Coffee.class);
        Coffee espresso = Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.0)).
                createTime(new Date()).updateTime(new Date()).build();
        Coffee saved = mongoTemplate.save(espresso);

        log.info("coffee{}",saved);

        List<Coffee> list = mongoTemplate.find(Query.query(Criteria.where("name").is("espresso")),Coffee.class);
        log.info("find {} coffee", list.size());
        list.forEach(c->log.info("coffee {}", c));
        Thread.sleep(1000);

        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("espresso")),new Update().set("price",Money.of(CurrencyUnit.of("CNY"), 25.0))
                .currentDate("updateTime"),Coffee.class);

        log.info("update Result:{}" ,updateResult.getModifiedCount());

        Coffee updateOne = mongoTemplate.findById(saved.getId(),Coffee.class);
        log.info("Update Result:{}", updateOne);
    }

    public void queryByRepository() throws InterruptedException {
        Coffee espresso = Coffee.builder().name("espresso").
                price(Money.of(CurrencyUnit.of("CNY"),25.00))
                .updateTime(new Date()).createTime(new Date()).build();
        Coffee latte = Coffee.builder().name("latte").
                price(Money.of(CurrencyUnit.of("CNY"),12.60))
                .updateTime(new Date()).createTime(new Date()).build();

        coffeeReposiotry.insert(Arrays.asList(espresso,latte));

        coffeeReposiotry.findAll(Sort.by("name")).forEach(c -> log.info("Saved Coffee {}", c));

        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"),35));
        latte.setUpdateTime(new Date());
        coffeeReposiotry.save(latte);

        coffeeReposiotry.findByName("latte").forEach(c -> log.info("coffee {}",c));

    }
}
