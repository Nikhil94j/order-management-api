package com.nprojects.ordermanagementapplication.controller;

import com.nprojects.ordermanagementapplication.model.CreateOrderRequest;
import com.nprojects.ordermanagementapplication.model.Order;
import com.nprojects.ordermanagementapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@io.swagger.v3.oas.annotations.security.SecurityRequirement(
        name = "bearerAuth"
)
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/")
    public String home(){
        return "Order Management API is running!";
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> result = orderService.getOrder(id);

        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody CreateOrderRequest request){
        return orderService.createOrder(request);
    }

    @PutMapping("/orders/{id}/quantity")
    public ResponseEntity<Void> updateQuantity(@PathVariable Long id,@RequestParam int quantity){
        orderService.updateQuantity(id,quantity);
        return ResponseEntity.ok().build();
    }

}
