package sizhe.chen.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import sizhe.chen.redis.mode.Coffee;
import sizhe.chen.redis.mode.CoffeeCache;
import sizhe.chen.redis.repository.CoffeeCacheRepository;
import sizhe.chen.redis.repository.CoffeeRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @ClassName : CoffeeService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 14:47
 */
@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeCacheRepository coffeeCacheRepository;

    public List<Coffee> findAllCoffee(){
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findSimpleCoffeeFromCache(String name){
        Optional<CoffeeCache> cached =coffeeCacheRepository.findOneByName(name);
        if (cached.isPresent()){
            CoffeeCache coffeeCache = cached.get();
            Coffee coffee = Coffee.builder().name(coffeeCache.getName()).price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee {} found in cache.", coffeeCache);
            return Optional.of(coffee);
        }else{
            Optional<Coffee> raw = findOneCoffee(name);
            raw.ifPresent(c -> {
                CoffeeCache coffeeCache = CoffeeCache.builder().id(c.getId()).name(c.getName())
                    .price(c.getPrice()).build();
                log.info("Save coffee  {}", coffeeCache);
                coffeeCacheRepository.save(coffeeCache);
            });
            return raw;
        }

    }

    public Optional<Coffee> findOneCoffee(String name){
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("name",exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.
                findOne(Example.of(Coffee.builder().name(name).build(),matcher));
        log.info("Coffee Fount: {}", coffee);
        return coffee;

    }

}
