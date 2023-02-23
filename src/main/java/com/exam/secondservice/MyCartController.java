package com.exam.secondservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/carts")
public class MyCartController {
    
    @Autowired
    public MyCartServiceImpl cartService;

    @PostMapping("")
    public ResponseEntity<MyCart> saveCart(
            @RequestBody MyCart cart) {
        MyCart cart1 = cartService
                .saveCart(cart);
        return new ResponseEntity<>(cart1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<MyCart> getAllProducts() {
        return cartService.getAllCarts();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<MyCart> getProductById(@PathVariable Long id) {
        MyCart cart1 = cartService.getCartById(id);
        if (cart1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MyCart>(cart1, HttpStatus.OK);
    }

     // @PutMapping("/{id}")
    // public ResponseEntity<CandidateAcademicInfo> updateAcademicInformation(@PathVariable Long id,
    //         @RequestBody CandidateAcademicInfo academicInfotmation) {
    //     CandidateAcademicInfo updatedAcademicInformation = academicInfoService.updateAcademicInformation(id,
    //             academicInfotmation);
    //     if (updatedAcademicInformation == null) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    //     } else {
    //         return new ResponseEntity<CandidateAcademicInfo>(updatedAcademicInformation, HttpStatus.OK);
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        MyCart product1 = cartService.getCartById(id);
        if (product1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cartService.deleteCartById(id);
            System.out.println("product deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/all")
    public void deleteCart() {
         cartService.deleteAllItemsInCart();
    }



}
