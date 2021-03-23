package com.example.demo.service;

import com.example.demo.Coupon.*;
import com.example.demo.modal.Cart;
import com.example.demo.modal.Product;
import com.example.demo.utility.CommonConstants;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ECommerceService{

    private Integer availableId = 0;
    private Coupon couponNextDiscount;
    private Coupon couponAllDiscount;
    private SpecialCoupon couponSpecificDiscount;
    private Map<String, Product> listOfProducts = new HashMap<>();
    private Cart cart;

    public ECommerceService(Coupon couponAllDiscount, Coupon couponNextDiscount,
                            SpecialCoupon couponSpecificDiscount, Cart cart){
        this.couponNextDiscount = couponNextDiscount;
        this.couponAllDiscount = couponAllDiscount;
        this.couponSpecificDiscount = couponSpecificDiscount;
        this.cart = cart;
    }

    public String registerProduct(final String productName, final Double productPrice){
        if(!listOfProducts.containsKey(productName)){
            listOfProducts.put(productName, new Product(++availableId, productName, productPrice));
            return CommonConstants.REGISTRATION_SUCCESS;
        }
        else {
            return CommonConstants.REGISTRATION_UNSUCCESSFUL;
        }
    }

    public String addItemsToCart(final String productName) throws CloneNotSupportedException{
        if(!listOfProducts.containsKey(productName)){
            return CommonConstants.ADD_TO_CART_UNSUCCESSFUL;
        }
        else{
            Product product = listOfProducts.get(productName);
            List<Product> itemList;
                if(cart.getCart().containsKey(productName)){
                    itemList = cart.getItem(productName);
                }
                else{
                    itemList = new ArrayList<>();
                }
                Product newProduct = getNewInstance(product);
                if(couponAllDiscount.isApplicable()){
                    couponAllDiscount.applyCoupon(newProduct);
                }
                if(couponNextDiscount.isApplicable()){
                    couponNextDiscount.applyCoupon(newProduct);
                }
                itemList.add(newProduct);
                cart.putItem(productName, itemList);
        }
        return CommonConstants.ADD_TO_CART_SUCCESSFUL;
    }

    public String addCoupon(CouponType coupon, int value){
        if(coupon == CouponType.COUPON_ALL_DISCOUNT){
            couponAllDiscount.addCoupon(value);
            for(String productName: cart.getCart().keySet()){
                List<Product> list = cart.getItem(productName);
                for(Product product: list){
                    couponAllDiscount.applyCoupon(product);
                }
            }
            return CommonConstants.COUPON_APPLIED_SUCCESSFULLY;
        }
        if(coupon == CouponType.COUPON_NEXT_DISCOUNT){
            couponNextDiscount.addCoupon(value);
            return CommonConstants.COUPON_APPLIED_SUCCESSFULLY;
        }
        return CommonConstants.COUPON_INVALID;
    }

    public String addSpecialCoupon(final String productName, CouponType coupon, int value, int index){
        if(coupon == CouponType.COUPON_SPECIFIC_DISCOUNT && listOfProducts.containsKey(productName)){
            return couponSpecificDiscount.applyCoupon(productName, value, index, cart);
        }
        return  CommonConstants.COUPON_INVALID;
    }

    public Double getCartValue(){
        double totalValue = 0.0;
        if(cart == null || cart.getCart().size()==0){
            return totalValue;
        }
        for(String productName: cart.getCart().keySet()){
            List<Product> list = cart.getItem(productName);
            for(Product product: list){
                totalValue += product.getProductSellingPrice();
            }
        }
        return new Double(totalValue);
    }

    private static Product getNewInstance(Product product) throws CloneNotSupportedException{
        return (Product)product.clone();
    }
}
