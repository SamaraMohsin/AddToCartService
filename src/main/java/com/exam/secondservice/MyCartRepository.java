package com.exam.secondservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyCartRepository extends JpaRepository<MyCart,Long> {
    
}
