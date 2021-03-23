package com.example.demo.Coupon;

import com.example.demo.modal.Product;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

@Component
public class CouponNextDiscount implements Coupon {

    private Deque<Integer> coupons = new LinkedList<>();

    public void addCoupon(Integer value){
        coupons.add(value);
    }

    public boolean isApplicable(){
        if(coupons.isEmpty()){
            return false;
        }
        return true;
    }

    public void applyCoupon(Product product){
        double value = product.getProductSellingPrice() * (100-coupons.removeFirst())/100;
        if(value > 0.0){
            product.setProductSellingPrice(value);
        }
    }
}
