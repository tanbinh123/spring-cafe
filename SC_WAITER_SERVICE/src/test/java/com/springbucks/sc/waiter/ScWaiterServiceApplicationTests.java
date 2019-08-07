package com.springbucks.sc.waiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ScWaiterServiceApplication.class})
@Slf4j
public class ScWaiterServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
}
