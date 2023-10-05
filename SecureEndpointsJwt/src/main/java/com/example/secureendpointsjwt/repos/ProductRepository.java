package com.example.secureendpointsjwt.repos;

import com.example.secureendpointsjwt.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

}
