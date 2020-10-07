package sizhe.chen.mogodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sizhe.chen.mogodb.bean.Coffee;

import java.util.List;

/**
 * @ClassName : CoffeReposiotry
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 14:17
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String>{

    List<Coffee> findByName(String name);
}
