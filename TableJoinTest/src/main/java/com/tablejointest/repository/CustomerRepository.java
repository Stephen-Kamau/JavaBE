package com.tablejointest.repository;


import com.tablejointest.dtos.OrderResponse;
import com.tablejointest.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("SELECT new com.tablejointest.dtos.OrderResponse(c.name , p.productName) FROM Customer c JOIN c.products p")
    public List<OrderResponse> getJoinInformation();

}

