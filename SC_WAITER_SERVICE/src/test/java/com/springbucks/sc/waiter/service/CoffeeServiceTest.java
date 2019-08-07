package com.springbucks.sc.waiter.service;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.springbucks.sc.waiter.mapper.CoffeeMapper;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@DBUnit
public class CoffeeServiceTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void saveCofeeTest() {
        TestCase.assertTrue(true);
    }
}
