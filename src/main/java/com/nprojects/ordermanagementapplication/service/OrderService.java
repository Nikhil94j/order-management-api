package com.nprojects.ordermanagementapplication.service;

import com.nprojects.ordermanagementapplication.model.CreateOrderRequest;
import com.nprojects.ordermanagementapplication.model.Order;
import com.nprojects.ordermanagementapplication.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public Order createOrder(CreateOrderRequest request){

        Order order=new Order();
        order.setProduct(request.getProduct());
        order.setQuantity(request.getQuantity());

        return orderRepository.save(order);

    }

    public Optional<Order> getOrder(Long id){
       return orderRepository.findById(id);
    }

    @Transactional
    public void updateQuantity(Long id,int quantity){
        Order order=orderRepository.findById(id).orElseThrow();
        order.setQuantity(quantity);
    }
}
