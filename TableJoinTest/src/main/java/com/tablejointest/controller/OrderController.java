package com.tablejointest.controller;

import com.tablejointest.dtos.OrderRequest;
import com.tablejointest.dtos.OrderResponse;
import com.tablejointest.models.Customer;
import com.tablejointest.repository.CustomerRepository;
import com.tablejointest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public Customer placeOrder(@RequestBody OrderRequest request){
        return customerRepository.save(request.getCustomer());
    }

    @GetMapping("/all")
    public List<Customer> findAllOrders(){
        return customerRepository.findAll();
    }

    @GetMapping("/get-infor")
    public List<OrderResponse> getJoinInformation(){
        return customerRepository.getJoinInformation();
    }
}
