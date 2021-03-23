package com.example.demo.modal;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cart {

    String id;
    public Map<String, List<Product>> cart;

    public Cart(final String id){
        this.id = id;
        cart = new HashMap<>();
    }

     public List<Product> getItem(final String productName){
        return cart.get(productName);
    }

    public void putItem(final String productName, List<Product> products){
         cart.put(productName, products);
    }

     public Map<String, List<Product>> getCart(){
        return cart;
     }
}
