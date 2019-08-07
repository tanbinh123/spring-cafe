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

    public Coffee save(String name, Money price) {
        Coffee coffee = Coffee.builder().name(name).price(price).build();
        if (coffeeMapper.insert(coffee) == 1) {
            return coffee;
        } else {
            return null;
        }
    }

    @Cacheable
    public List<Coffee> getAll() {
        return coffeeMapper.getAll();
    }

    public Coffee getById(Long id) {
        return coffeeMapper.getById(id);
    }

    public Coffee getByName(String name) {
        return coffeeMapper.getByName(name);
    }

    public List<Coffee> getAllByName(String name) {
        return coffeeMapper.getAllByName(name);
    }
}
