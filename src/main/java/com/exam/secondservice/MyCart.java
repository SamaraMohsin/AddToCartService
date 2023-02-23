package com.exam.secondservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="CartDetails")
@AllArgsConstructor
@NoArgsConstructor
public class MyCart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_name", nullable = false)
    private String prodName;

    @Column(name = "product_image", columnDefinition= "Text")
    private String prodImg;

    @Column(name = "product_quantity", nullable = false)
    private Integer prodQuantity;

    @Column(name = "unit_price", nullable = false)
    private Long unitPrice;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

}
