package com.nprojects.ordermanagementapplication.controller;

import com.nprojects.ordermanagementapplication.model.CreateOrderRequest;
import com.nprojects.ordermanagementapplication.model.Order;
import com.nprojects.ordermanagementapplication.model.OrderResponse;
import com.nprojects.ordermanagementapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id,
                                          @AuthenticationPrincipal Jwt jwt) {

        Long userId=Long.parseLong(jwt.getSubject());

        Optional<OrderResponse> result = orderService.getOrder(id,userId);

        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request,
                                     @AuthenticationPrincipal Jwt jwt){

        Long userId=Long.parseLong(jwt.getSubject());
        return orderService.createOrder(request,userId);
    }

    @PutMapping("/orders/{id}/quantity")
    public ResponseEntity<Void> updateQuantity(@PathVariable Long id,
                                               @RequestParam int quantity,
                                               @AuthenticationPrincipal Jwt jwt){

        Long userId=Long.parseLong(jwt.getSubject());

        boolean updated=orderService.updateQuantity(id,quantity,userId);

        if(!updated){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
