package sizhe.chen.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.redis.model.Coffee;
import sizhe.chen.redis.model.CoffeeOrder;
import sizhe.chen.redis.model.OrderState;
import sizhe.chen.redis.repository.CoffeeOrderRepository;

import java.util.Arrays;

/**
 * @ClassName : CoffeeOrderService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 10:16
 */
@Service
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;
    @Transactional
    public CoffeeOrder createOrder(String customer,
                                   Coffee... coffees){
        CoffeeOrder order =CoffeeOrder.builder().
                customer(customer).items(Arrays.asList(coffees)).build();

        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("new Order: {}", saved);
        return saved;

    }
    @Transactional
    public boolean updateState(OrderState state,CoffeeOrder order){
        if(state.compareTo(order.getState()) <=0){
            log.warn("Wrong state order:{} ,{}" , state, order.getState());
            return false;
        }

        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("update Order : {}", order);
        return true;
    }
}
