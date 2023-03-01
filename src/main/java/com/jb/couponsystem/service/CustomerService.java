package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Coupon purchaseCoupon(UUID token, int couponId) throws CouponSystemException;
    Coupon getSingleCoupon(UUID token,int couponId) throws CouponSystemException;
    List<Coupon> getCustomerCouponsById(UUID token) throws CouponSystemException;
    List<Coupon> getAllCustomerCouponsBySpecificCategory(UUID token,String category) throws CouponSystemException;
    List<Coupon> getAllCustomerCouponByMaxPrice(UUID token,double price) throws CouponSystemException;
    Customer getCustomerDetails(UUID token) throws CouponSystemException;
    List<Coupon>getAllCoupons(UUID token) throws CouponSystemException;
}
