package sizhe.chen.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.redis.model.CoffeeOrder;

/**
 * @ClassName : CoffeeOrderRepository
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 09:56
 */
public  interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
}
