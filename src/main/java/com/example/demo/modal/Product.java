package com.example.demo.modal;


import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

@Component
public class Product implements Cloneable{

    private String productName;
    private Integer productId;
    private Double productPrice;
    private Double productSellingPrice;

    private Deque<Integer> coupons;

    public Product(final Integer productId,final String productName, final Double productPrice){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSellingPrice = productPrice;
        coupons = new LinkedList<>();
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(Double productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }


    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
