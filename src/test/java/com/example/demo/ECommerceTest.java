package com.example.demo;

import com.example.demo.Coupon.*;
import com.example.demo.controller.ECommerceController;
import com.example.demo.modal.Cart;
import com.example.demo.service.ECommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

public class ECommerceTest {

    private ECommerceController eCommerceController;


    public static final String CART_1 = "Cart1";
    public static final String STAPLER = "STAPLER";
    public static final String TABLE = "TABLE";
    public static final String CHAIR = "CHAIR";
    public static final String MOBILE_PHONE = "MOBILE_PHONE";
    public static final String LAPTOP = "LAPTOP";


    @BeforeEach
    public void setup(){
        Coupon couponNextDiscount = new CouponNextDiscount();
        Coupon couponAllDiscount = new CouponAllDiscount();
        SpecialCoupon couponSpecificDiscount = new CouponSpecificDiscount();
        Cart cart = new Cart(CART_1);
        ECommerceService eCommerceService = new ECommerceService(couponAllDiscount, couponNextDiscount, couponSpecificDiscount, cart);
        this.eCommerceController = new ECommerceController(eCommerceService);
    }

    @Test
    public void testECommerce() {

        System.out.println(eCommerceController.registerProduct(STAPLER, new Double(10.0)));
        System.out.println(eCommerceController.registerProduct(TABLE, new Double(5000.0)));
        System.out.println(eCommerceController.registerProduct(CHAIR, new Double(2500.0)));
        System.out.println(eCommerceController.registerProduct(MOBILE_PHONE, new Double(50000.0)));
        System.out.println(eCommerceController.registerProduct(LAPTOP, new Double(100000.0)));

        try {
            System.out.println(eCommerceController.addItemsToCart(STAPLER));
            System.out.println(eCommerceController.addCoupon(CouponType.COUPON_ALL_DISCOUNT, 25));
            System.out.println(eCommerceController.addCoupon(CouponType.COUPON_NEXT_DISCOUNT, 10));
            System.out.println(eCommerceController.addItemsToCart(STAPLER));
            System.out.println(eCommerceController.addSpecialCoupon(STAPLER, CouponType.COUPON_SPECIFIC_DISCOUNT,2,2));
            System.out.println("Total Cart Value: "+eCommerceController.getCartValue());
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testECommerceForAnotherInput() {

        System.out.println(eCommerceController.registerProduct(STAPLER, new Double(10.0)));
        System.out.println(eCommerceController.registerProduct(TABLE, new Double(5000.0)));
        System.out.println(eCommerceController.registerProduct(CHAIR, new Double(2500.0)));
        System.out.println(eCommerceController.registerProduct(MOBILE_PHONE, new Double(50000.0)));
        System.out.println(eCommerceController.registerProduct(LAPTOP, new Double(100000.0)));

        try {
            System.out.println(eCommerceController.addItemsToCart(STAPLER));
            System.out.println(eCommerceController.addItemsToCart(MOBILE_PHONE));
            System.out.println(eCommerceController.addItemsToCart(LAPTOP));
            System.out.println(eCommerceController.addItemsToCart(CHAIR));
            System.out.println(eCommerceController.addItemsToCart(TABLE));
            System.out.println(eCommerceController.addItemsToCart(STAPLER));
            System.out.println(eCommerceController.addCoupon(CouponType.COUPON_ALL_DISCOUNT, 25));
            System.out.println(eCommerceController.addCoupon(CouponType.COUPON_NEXT_DISCOUNT, 10));
            System.out.println(eCommerceController.addItemsToCart(STAPLER));
            System.out.println(eCommerceController.addSpecialCoupon(STAPLER, CouponType.COUPON_SPECIFIC_DISCOUNT,2,2));
            System.out.println("Total Cart Value: "+eCommerceController.getCartValue());
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
