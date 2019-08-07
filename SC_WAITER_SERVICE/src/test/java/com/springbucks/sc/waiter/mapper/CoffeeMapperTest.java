package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.Coffee;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CoffeeMapperTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void testInsert() throws Exception {
        coffeeMapper.insert(Coffee.builder().name ("Americano").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build());
        coffeeMapper.insert(Coffee.builder().name ("Mocha").price(Money.of(CurrencyUnit.of("CNY"), 35.0)).build());
        coffeeMapper.insert(Coffee.builder().name ("Latte").price(Money.of(CurrencyUnit.of("CNY"), 28.0)).build());
        List<Coffee> coffeeList = coffeeMapper.getAll();
        for (Coffee c:coffeeList) {
            log.info("========Coffee Record==========");
            log.info(c.toString());
        }
        TestCase.assertEquals(3, coffeeMapper.getAll().size());
    }

}
