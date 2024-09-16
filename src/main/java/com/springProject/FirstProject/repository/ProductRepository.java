package com.springProject.FirstProject.repository;

import com.springProject.FirstProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}
