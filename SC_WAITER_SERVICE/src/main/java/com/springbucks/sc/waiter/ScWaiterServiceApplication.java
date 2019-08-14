package com.springbucks.sc.waiter;

import com.springbucks.sc.waiter.controller.PerformanceInteceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.springbucks.sc.waiter.mapper")
//@EnableCaching
@EnableDiscoveryClient
public class ScWaiterServiceApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(ScWaiterServiceApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceInteceptor())
				.addPathPatterns("/coffee/**").addPathPatterns("/order/**");
	}
}
