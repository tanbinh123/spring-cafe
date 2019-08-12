package com.springbucks.sc.customer;

import com.springbucks.sc.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class CustomRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
//        Long id = orderCoffee();
//        queryOrder(id);
    }

    private void readMenu() {
        log.info("Reading coffee menu...");
        ParameterizedTypeReference<List<Coffee>> ptr =
                new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> list = restTemplate
                .exchange("http://localhost:8081/coffee/", HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("Coffee: {}", c));
    }

//    private Long orderCoffee() {
//        NewOrderRequest orderRequest = NewOrderRequest.builder()
//                .customer("Li Lei")
//                .items(Arrays.asList("capuccino"))
//                .build();
//        RequestEntity<NewOrderRequest> request = RequestEntity
//                .post(UriComponentsBuilder.fromUriString("http://localhost:8080/order/").build().toUri())
//                .body(orderRequest);
//        ResponseEntity<CoffeeOrder> response = restTemplate.exchange(request, CoffeeOrder.class);
//        log.info("Order Request Status Code: {}", response.getStatusCode());
//        Long id = response.getBody().getId();
//        log.info("Order ID: {}", id);
//        return id;
//    }
//
//    private void queryOrder(Long id) {
//        CoffeeOrder order = restTemplate
//                .getForObject("http://localhost:8080/order/{id}", CoffeeOrder.class, id);
//        log.info("Order: {}", order);
//    }
}
