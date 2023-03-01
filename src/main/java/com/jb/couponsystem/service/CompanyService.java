package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.exception.CouponSystemException;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    Coupon addCoupon(UUID token, Coupon coupon) throws CouponSystemException;
    Coupon updateCoupon(UUID token,int couponId,Coupon coupon) throws CouponSystemException;
    void deleteCoupon(UUID token, int couponId) throws CouponSystemException;
    Coupon getSingleCoupon(UUID token,int couponId) throws CouponSystemException;
    List<Coupon> getAllCouponsByCompanyId(UUID token) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsBySpecificCategory(UUID token, String category) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponByMaxPrice(UUID token, double price) throws CouponSystemException;
    Company getCompanyDetails(UUID token) throws CouponSystemException;
}
