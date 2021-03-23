package com.example.demo.controller;

import com.example.demo.Coupon.CouponType;
import com.example.demo.service.ECommerceService;
import org.springframework.stereotype.Controller;

@Controller
public class ECommerceController {

    private ECommerceService eCommerceService;

    public ECommerceController(ECommerceService eCommerceService){
        this.eCommerceService = eCommerceService;
    }

    public String registerProduct(final String productName, final Double productPrice){
        return eCommerceService.registerProduct(productName,productPrice);
    }

    public String addItemsToCart(final String productName) throws CloneNotSupportedException{
        return eCommerceService.addItemsToCart(productName);
    }

   public String addCoupon(CouponType coupon, int value){
        return eCommerceService.addCoupon(coupon,value);
   }

   public Double getCartValue(){
        return eCommerceService.getCartValue();
   }

   public String addSpecialCoupon(final String productName, CouponType coupon, int value, int index) {
        return eCommerceService.addSpecialCoupon(productName,coupon, value, index);
   }
}
