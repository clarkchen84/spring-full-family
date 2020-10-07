package sizhe.chen.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.redis.mode.Coffee;
import sizhe.chen.redis.mode.CoffeeOrder;
import sizhe.chen.redis.mode.OrderState;
import sizhe.chen.redis.repository.CoffeeOrderRepository;

import java.util.Arrays;

/**
 * @ClassName : CoffeeOrderService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 14:24
 */
@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Transactional
    public CoffeeOrder createOrder(String customer, Coffee... coffees){
        CoffeeOrder order = CoffeeOrder.builder().customer(customer).items(Arrays.asList(coffees))
                .state(OrderState.INIT).build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order :{}", saved);
        return  saved;
    }

    @Transactional
    public boolean updateState(CoffeeOrder order,OrderState state){
        if(state.compareTo(order.getState()) <= 0){
            log.warn("wrong state order : {} , {}", state, order.getState());
            return false;
        }

        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("udate Order : {}", order);
        return true;
    }
}
