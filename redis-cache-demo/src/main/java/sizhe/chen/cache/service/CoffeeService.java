package sizhe.chen.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import sizhe.chen.cache.model.Coffee;
import sizhe.chen.cache.repository.CoffeeRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @ClassName : CoffeeService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 20:02
 */
@Service
@CacheConfig(cacheNames = "coffee")
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;


    @Cacheable//追加缓存
    public List<Coffee> findAllCoffee(){
        return  coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name){
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(),matcher));

        log.info("Coffee Fount: {}",coffee);
        return coffee;
    }

    @CacheEvict// 缓存清理
    public void reloadCoffee(){

    }
}
