package com.springbucks.sc.waiter.controller;

import com.springbucks.sc.waiter.controller.request.NewOrderRequest;
import com.springbucks.sc.waiter.model.Coffee;
import com.springbucks.sc.waiter.model.CoffeeOrder;
import com.springbucks.sc.waiter.service.CoffeeOrderService;
import com.springbucks.sc.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        CoffeeOrder order = orderService.getOrder(id);
        log.info("Get order: {}", order);
        return order;
    }

    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
