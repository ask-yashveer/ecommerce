package com.example.demo.Coupon;

import com.example.demo.modal.Product;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

@Component
public class CouponAllDiscount implements Coupon {

    private Deque<Integer> coupons = new LinkedList<>();

    private Boolean activeCoupon = Boolean.FALSE;

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
        for(Integer discountValue: coupons) {
            double value = product.getProductSellingPrice() * (100 - discountValue)/100;
            if (value > 0.0) {
                product.setProductSellingPrice(value);
            }
        }
    }
}
