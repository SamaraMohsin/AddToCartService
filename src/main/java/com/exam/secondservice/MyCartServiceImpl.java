package com.exam.secondservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MyCartServiceImpl implements MyCartService{
    
    @Autowired
    public MyCartRepository cartRepo;

    public MyCart saveCart(MyCart cart) {
        return cartRepo.save(cart);
    }

    public List<MyCart> getAllCarts() {
        return cartRepo.findAll();
    }

    public MyCart getCartById(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    public void deleteCartById(Long id) {
        cartRepo.deleteById(id);
    }

    void deleteAllItemsInCart() {
        cartRepo.deleteAll(); 
    }

    


}
