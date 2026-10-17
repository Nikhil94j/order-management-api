package com.nprojects.ordermanagementapplication.service;

import com.nprojects.ordermanagementapplication.model.CreateOrderRequest;
import com.nprojects.ordermanagementapplication.model.Order;
import com.nprojects.ordermanagementapplication.model.OrderResponse;
import com.nprojects.ordermanagementapplication.model.User;
import com.nprojects.ordermanagementapplication.repository.OrderRepository;
import com.nprojects.ordermanagementapplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,UserRepository userRepository){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request, Long userId){

        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));

        Order order=new Order();
        order.setProduct(request.getProduct());
        order.setQuantity(request.getQuantity());
        order.setUser(user);

        Order savedOrder= orderRepository.save(order);

        return toOrderResponse(savedOrder);
    }

    public Optional<OrderResponse> getOrder(Long id,Long userId){

       return orderRepository.findByIdAndUser_Id(id,userId)
               .map(this::toOrderResponse);
    }

    @Transactional
    public boolean updateQuantity(Long id,int quantity,Long userId){
        Order order=orderRepository.findByIdAndUser_Id(id,userId).orElse(null);

        if(order==null){
            return false;
        }

        order.setQuantity(quantity);
        return true;
    }

    private OrderResponse toOrderResponse(Order order){
        return new OrderResponse(
                order.getId(),order.getProduct(),order.getQuantity()
        );
    }
}
