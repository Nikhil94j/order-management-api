package com.nprojects.ordermanagementapplication.repository;

import com.nprojects.ordermanagementapplication.model.CreateOrderRequest;
import com.nprojects.ordermanagementapplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

     Order save(Order order);

    Optional<Order> findById(Long id);

    Optional<Order>  findByIdAndUser_Id(Long id,Long userId);
}
