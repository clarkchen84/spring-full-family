package sizhe.chen.coffeebucks.repository;

import org.springframework.data.repository.CrudRepository;
import sizhe.chen.coffeebucks.bean.Coffee;

import java.util.List;

public interface CoffeeRepository  extends CrudRepository<Coffee,Long> {

    public List<Coffee> findCoffeeByName(String name);
}
