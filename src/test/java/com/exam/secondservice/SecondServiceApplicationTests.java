package com.exam.secondservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecondServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getAndSetProductID(){
		MyCart cut = new MyCart();
		Long cartId = 1L;
		cut.setId(cartId);
		assertEquals(cartId, cut.getId());
	}

	@Test
	void getAndSetProductQunatity(){
		MyCart cut = new MyCart();
		Long prodQuantity = 684750000L;
		cut.setProdQuantity(prodQuantity);
		assertEquals(prodQuantity, cut.getProdQuantity());
	}

	@Test
	void getAndSetTotalPrice(){
		MyCart cut = new MyCart();
		Long totalPrice = 684750000L;
		cut.setTotalPrice(totalPrice);
		assertEquals(totalPrice, cut.getTotalPrice());
	}


}
