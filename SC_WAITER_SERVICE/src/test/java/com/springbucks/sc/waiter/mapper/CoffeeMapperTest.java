package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.Coffee;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoffeeMapperTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void test01Insert() {
        log.info("======== Test01: Insert =========");
        List<Coffee> coffeeList = coffeeMapper.getAll();
        log.info("Initial number of coffee: " + coffeeList.size());

        coffeeMapper.insert(Coffee.builder().name ("Americano").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build());
        coffeeMapper.insert(Coffee.builder().name ("Mocha").price(Money.of(CurrencyUnit.of("CNY"), 35.0)).build());
        coffeeMapper.insert(Coffee.builder().name ("Latte").price(Money.of(CurrencyUnit.of("CNY"), 28.0)).build());

        coffeeList = coffeeMapper.getAll();
        for (Coffee c:coffeeList) {
            log.info("Inserted Coffee {}", c);
        }
        TestCase.assertEquals(3, coffeeList.size());
    }

    @Test
    public void test02GetByName() {
        log.info("======== Test02: GetByName =========");

        Coffee coffee = coffeeMapper.getByName("Americano");
        TestCase.assertEquals("Americano", coffee.getName());
        TestCase.assertEquals(Money.of(CurrencyUnit.of("CNY"), 20.0), coffee.getPrice());
    }

    @Test
    public void test03GetByNames() {
        log.info("======== Test03: GetByNames =========");

        List<String> coffeeNameList = new ArrayList<>();
        coffeeNameList.add("Americano");
        coffeeNameList.add("Mocha");
        TestCase.assertEquals(2, coffeeMapper.getByNames(coffeeNameList).size());
    }

    @Test
    public void test04Delete() {
        log.info("======== Test04: Delete =========");
        List<Coffee> coffeeList = coffeeMapper.getAll();
        log.info("Initial number of coffee: " + coffeeList.size());
        if (!coffeeList.isEmpty()) {
            List<String> coffeeNameList = new ArrayList<>();
            coffeeNameList.add("Americano");
            coffeeNameList.add("Mocha");
            coffeeNameList.add("Latte");
            for (Coffee c : coffeeMapper.getByNames(coffeeNameList)) {
                coffeeMapper.deleteById(c.getId());
            }
            TestCase.assertEquals(0, coffeeMapper.getAll().size());
        }
    }
}
