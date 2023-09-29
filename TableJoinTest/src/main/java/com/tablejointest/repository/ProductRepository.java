package com.tablejointest.repository;

import com.tablejointest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findByProductName(String title);
}
