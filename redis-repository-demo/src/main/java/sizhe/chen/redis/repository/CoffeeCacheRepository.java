package sizhe.chen.redis.repository;

import org.springframework.data.repository.CrudRepository;
import sizhe.chen.redis.mode.CoffeeCache;

import java.util.Optional;

/**
 * @ClassName : CoffeeCacheRepository
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 14:37
 */
public interface  CoffeeCacheRepository  extends CrudRepository<CoffeeCache,Long> {
    Optional<CoffeeCache> findOneByName(String name);
}
