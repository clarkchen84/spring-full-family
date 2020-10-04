package sizhe.chen.coffeebucks.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.coffeebucks.bean.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
