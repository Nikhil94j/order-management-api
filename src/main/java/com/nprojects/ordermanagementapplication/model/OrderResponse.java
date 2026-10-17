package com.nprojects.ordermanagementapplication.model;

public class OrderResponse {

    private Long id;
    private String product;
    private int quantity;

    public OrderResponse(Long id,String product,int quantity){
        this.id=id;
        this.product=product;
        this.quantity=quantity;
    }

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
