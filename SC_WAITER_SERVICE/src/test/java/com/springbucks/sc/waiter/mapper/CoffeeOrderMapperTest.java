package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.Coffee;
import com.springbucks.sc.waiter.model.CoffeeOrder;
import com.springbucks.sc.waiter.model.OrderState;
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
public class CoffeeOrderMapperTest {
    @Autowired
    private CoffeeOrderMapper coffeeOrderMapper;

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void test01Insert() {
        log.info("======== Test01: Insert =========");
        List<CoffeeOrder> orderList = coffeeOrderMapper.getAll();
        log.info("Initial number of orders: " + orderList.size());

        List<String> coffeeNameList = new ArrayList<>();
        coffeeNameList.add("Americano");
        coffeeNameList.add("Mocha");
        List<Coffee> coffeeList = coffeeMapper.getByNames(coffeeNameList);
        if (coffeeList.isEmpty()) {
            coffeeMapper.insert(Coffee.builder().name ("Americano").price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build());
            coffeeMapper.insert(Coffee.builder().name ("Mocha").price(Money.of(CurrencyUnit.of("CNY"), 35.0)).build());
            coffeeList = coffeeMapper.getByNames(coffeeNameList);
        }

        coffeeOrderMapper.insert(CoffeeOrder.builder().customer ("Bryan").items(coffeeList).state(OrderState.INIT).build());
        coffeeOrderMapper.insert(CoffeeOrder.builder().customer ("Jay").items(coffeeList).state(OrderState.INIT).build());

        orderList = coffeeOrderMapper.getAll();
        for (CoffeeOrder o:orderList) {
            log.info("Inserted Order {}", o);
        }
        TestCase.assertEquals(2, orderList.size());
    }

    @Test
    public void test02GetAllByCustomer() {
        log.info("======== Test02: GetAllByCustomer =========");
        List<CoffeeOrder> orderList1 = coffeeOrderMapper.getAllByCustomer("Bryan");
        TestCase.assertEquals(1, orderList1.size());
        List<CoffeeOrder> orderList2 = coffeeOrderMapper.getAllByCustomer("Jay");
        TestCase.assertEquals(1, orderList2.size());
    }

    @Test
    public void test03Delete() {
        log.info("======== Test03: Delete =========");
        List<CoffeeOrder> orderList = coffeeOrderMapper.getAll();
        log.info("Initial number of orders: " + orderList.size());
        if (!orderList.isEmpty()) {

            for (CoffeeOrder o : orderList) {
                coffeeOrderMapper.deleteById(o.getId());
            }

            TestCase.assertEquals(0, coffeeOrderMapper.getAll().size());
        }
        // Cleanup the coffees inserted in this test suite
        List<String> coffeeNameList = new ArrayList<>();
        coffeeNameList.add("Americano");
        coffeeNameList.add("Mocha");
        for (Coffee c:coffeeMapper.getByNames(coffeeNameList)) {
            coffeeMapper.deleteById(c.getId());
        }
    }
}
