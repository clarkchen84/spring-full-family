package sizhe.chen.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.redis.model.Coffee;

/**
 * @ClassName : CoffeeRepository
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 09:55
 */
public interface  CoffeeRepository extends JpaRepository<Coffee,Long> {
}
