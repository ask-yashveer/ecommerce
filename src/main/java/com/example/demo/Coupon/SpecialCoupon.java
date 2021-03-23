package com.example.demo.Coupon;

import com.example.demo.modal.Cart;
import com.example.demo.modal.Product;

import java.util.List;
import java.util.Map;

public interface SpecialCoupon {

    public String applyCoupon(final String productName, double value, int index, Cart cart);
}
