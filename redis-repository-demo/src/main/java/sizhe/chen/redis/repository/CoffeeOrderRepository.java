package sizhe.chen.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.redis.mode.CoffeeOrder;

/**
 * @ClassName : CoffeeOrderReposiotry
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 11:43
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
