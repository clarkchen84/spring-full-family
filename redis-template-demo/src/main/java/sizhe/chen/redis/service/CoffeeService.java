package sizhe.chen.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sizhe.chen.redis.model.Coffee;
import sizhe.chen.redis.repository.CoffeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @ClassName : CoffeeService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 09:57
 */
@Service
@Slf4j
public class CoffeeService {

    private static final String CACHE = "springbucks-coffee";
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee(){
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name){
        HashOperations<String,String,Coffee> hashOperations = redisTemplate.opsForHash();

        if(redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)){
            log.info("Get Coffee {} from Redis.", name);
            return Optional.of(hashOperations.get(CACHE,name));
        }

        ExampleMatcher macher = ExampleMatcher.matching().
                withMatcher("name",exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(
                Coffee.builder().name(name).build(), macher));
        log.info("Coffee {}:", coffee);

        if(coffee.isPresent()){
            log.info("put coffee {} to reids", coffee);
            hashOperations.put(CACHE,name,coffee.get());
            redisTemplate.expire(CACHE,1, TimeUnit.MINUTES);
        }
        return coffee;
    }

}
