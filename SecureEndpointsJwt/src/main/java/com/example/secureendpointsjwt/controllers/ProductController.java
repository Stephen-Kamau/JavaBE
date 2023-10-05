package com.example.secureendpointsjwt.controllers;

import com.example.secureendpointsjwt.models.Products;
import com.example.secureendpointsjwt.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repo;

    @PostMapping
    public ResponseEntity<Products> create(@RequestBody @Valid Products product) {
        Products savedProduct = repo.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }

    @GetMapping
    public List<Products> list() {
        return repo.findAll();
    }
}
