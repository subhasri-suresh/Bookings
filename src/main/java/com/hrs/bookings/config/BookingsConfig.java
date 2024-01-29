package com.hrs.bookings.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(basePackages = "com.hrs.bookings.dao", entityManagerFactoryRef="entityManagerFactory")
@ComponentScan(basePackages = {"com.hrs.bookings.controller", "com.hrs.bookings.dao", "com.hrs.bookings.service"})
@EntityScan("com.hrs.bookings.entity")
public class BookingsConfig {


}
