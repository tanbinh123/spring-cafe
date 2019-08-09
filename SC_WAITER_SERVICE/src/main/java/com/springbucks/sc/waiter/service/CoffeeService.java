package com.springbucks.sc.waiter.service;

import com.springbucks.sc.waiter.mapper.CoffeeMapper;
import com.springbucks.sc.waiter.model.Coffee;
import org.joda.money.Money;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public Coffee saveCoffee(String name, Money price) {
        Coffee coffee = Coffee.builder().name(name).price(price).build();
        if (coffeeMapper.insert(coffee) == 1) {
            return coffee;
        } else {
            return null;
        }
    }

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeMapper.getAll();
    }

    public Coffee getCoffeeById(Long id) {
        return coffeeMapper.getById(id);
    }

    public Coffee getOneCoffeeByName(String name) { return coffeeMapper.getOneByName(name); }

    public List<Coffee> getAllCoffeeByName(String name) { return coffeeMapper.getAllByName(name); }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeMapper.getByNames(names);
    }
}
