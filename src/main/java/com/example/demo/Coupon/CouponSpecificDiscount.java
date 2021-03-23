package com.example.demo.Coupon;

import com.example.demo.modal.Cart;
import com.example.demo.modal.Product;
import com.example.demo.utility.CommonConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponSpecificDiscount implements SpecialCoupon {

    public String applyCoupon(final String productName, double value, int index, Cart cart){
        List<Product> products = cart.getItem(productName);
        if(products.size()<index){
            return CommonConstants.COUPON_INVALID;
        }
        Product product = products.get(index-1);
        double expectedValueAfterDiscount = product.getProductSellingPrice() - value;
        if(value > 0.0){
            product.setProductSellingPrice(expectedValueAfterDiscount);
            return CommonConstants.COUPON_APPLIED_SUCCESSFULLY;
        }
        return CommonConstants.COUPON_INVALID;
    }
}
