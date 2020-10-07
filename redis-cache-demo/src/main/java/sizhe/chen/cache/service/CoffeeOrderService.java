package sizhe.chen.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.cache.model.Coffee;
import sizhe.chen.cache.model.CoffeeOrder;
import sizhe.chen.cache.model.OrderState;
import sizhe.chen.cache.repository.CoffeeOrderRepository;

import java.util.Arrays;

/**
 * @ClassName : CoffeeOrderService
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 20:09
 */

@Service
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;
    @Transactional
    public CoffeeOrder createOrder(String custumer, Coffee... coffee){
        CoffeeOrder order = CoffeeOrder.builder().customer(custumer)
                .items(Arrays.asList(coffee)).state(OrderState.INIT).build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("new Order:{}",saved);
        return  saved;
    }

    public boolean updateOrder(CoffeeOrder coffeeOrder , OrderState state){
        if(state.compareTo(coffeeOrder.getState()) <= 0){
            return false;
        }

        coffeeOrder.setState(state);
        coffeeOrderRepository.save(coffeeOrder);

        log.info("Updated order:{}",coffeeOrder);
        return true;
    }
}
