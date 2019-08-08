package com.springbucks.sc.waiter.service;

import com.springbucks.sc.waiter.mapper.CoffeeOrderMapper;
import com.springbucks.sc.waiter.model.Coffee;
import com.springbucks.sc.waiter.model.CoffeeOrder;
import com.springbucks.sc.waiter.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderMapper coffeeOrderMapper;

    public CoffeeOrder getOrder(Long id) {
        return coffeeOrderMapper.getById(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        if (coffeeOrderMapper.insert(order) == 1) {
            log.info("New Order: {}", order);
            return order;
        } else {
            return null;
        }
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        coffeeOrderMapper.insert(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
