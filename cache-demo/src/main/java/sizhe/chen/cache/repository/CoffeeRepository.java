package sizhe.chen.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.cache.model.Coffee;

/**
 * @ClassName : CoffeeRepository
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 19:59
 */
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {

}
