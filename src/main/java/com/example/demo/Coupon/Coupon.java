package com.example.demo.Coupon;

import com.example.demo.modal.Product;
import org.springframework.stereotype.Component;

@Component
public interface Coupon {

    public void addCoupon(Integer value);
    public void applyCoupon(Product item);
    public boolean isApplicable();
}
