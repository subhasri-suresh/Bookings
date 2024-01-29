package com.hrs.bookings;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = BookingsApplication.class)
class BookingsApplicationTests {

	@Test
	void contextLoads() {
	}

}
