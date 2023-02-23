package com.exam.secondservice;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc

public class SecondServiceControllerTests {
    
    private MockMvc mvc;

    @Mock
    private MyCartServiceImpl cartService;


	@InjectMocks
	private MyCartController controller;

	private JacksonTester<MyCart> json;
	private JacksonTester<Collection<MyCart>> jsonList;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

    @Test
	void contextLoads() {
	}

    @Test
	public void canSaveCart() throws Exception {
		MyCart cart1 = new MyCart(1L,"ball","img1",2L,200L,400L);
		when(cartService.saveCart(cart1)).thenReturn(cart1);
		mvc.perform(post("/api/carts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(cart1).getJson()))
				.andExpect(status().isCreated());
	}


    @Test
	public void canGetAllCartDetails() throws Exception {
        MyCart cart1 = new MyCart(1L,"ball","img1",2L,200L,400L);

        MyCart cart2 = new MyCart(1L,"bat","img1",2L,200L,400L);

		List<MyCart> allCarts = new ArrayList<MyCart>();
		allCarts.add(cart1);
		allCarts.add(cart2);
		when(cartService.getAllCarts()).thenReturn(allCarts);
		mvc.perform(get("/api/carts/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonList.write(allCarts).getJson()));
	}

    @Test
	public void canGetCartById() throws Exception {
        MyCart cart1 = new MyCart(1L,"ball","img1",2L,200L,400L);

		when(cartService.getCartById(1L)).thenReturn(cart1);
		mvc.perform(get("/api/carts/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(cart1).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().json(json.write(cart1).getJson()));

	}

    @Test
	public void canDeleteCartbyId() throws Exception {
        MyCart cart1 = new MyCart(1L,"ball","img1",2L,200L,400L);
		when(cartService.getCartById(1L)).thenReturn(cart1);
		mvc.perform(delete("/api/carts/1"))
			.andExpect(status().isNoContent());
	}

    @Test
	public void canDeleteAll() throws Exception {
        MyCart cart1 = new MyCart(1L,"ball","img1",2L,200L,400L);

        MyCart cart2 = new MyCart(1L,"bat","img1",2L,200L,400L);

		List<MyCart> allCarts = new ArrayList<MyCart>();
		allCarts.add(cart1);
		allCarts.add(cart2);
		when(cartService.getAllCarts()).thenReturn(allCarts);
        mvc.perform(delete("/api/carts/delete/all"))
			.andExpect(status().isOk());
	}


}
