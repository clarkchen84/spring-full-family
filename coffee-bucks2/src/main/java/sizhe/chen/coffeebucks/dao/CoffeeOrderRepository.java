package sizhe.chen.coffeebucks.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.coffeebucks.bean.CoffeeOrder;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
