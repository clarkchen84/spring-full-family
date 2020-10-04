package sizhe.chen.coffeebucks.repository;

import org.springframework.data.repository.CrudRepository;
import sizhe.chen.coffeebucks.bean.Coffee;
import sizhe.chen.coffeebucks.bean.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder,Long> {

    public List<CoffeeOrder> findCoffeeOrdersByCustomer(String order);
}
