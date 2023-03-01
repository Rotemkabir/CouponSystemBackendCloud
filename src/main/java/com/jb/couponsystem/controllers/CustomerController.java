package com.jb.couponsystem.controllers;

import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all/coupons")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return customerService.getAllCoupons(token);
    }

    @PostMapping("/coupons/{couponId}")
    public Coupon purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        return customerService.purchaseCoupon(token, couponId);
    }

    @GetMapping("coupons/{couponId}")
    public Coupon getSingleCoupon(@RequestHeader("Authorization") UUID token,@PathVariable int couponId) throws CouponSystemException {
        return customerService.getSingleCoupon(token,couponId);
    }

    @GetMapping("/coupons")
    public List<Coupon> getCouponByCustomerId(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return customerService.getCustomerCouponsById(token);
    }

    @GetMapping("/coupons/by/category")
    public List<Coupon> getCustomerCouponByCategory(@RequestHeader("Authorization") UUID token, @RequestParam String category) throws CouponSystemException {
        return customerService.getAllCustomerCouponsBySpecificCategory(token,category);
    }

    @GetMapping("/coupons/price/under")
    public List<Coupon> getCustomerCouponByPrice(@RequestHeader("Authorization") UUID token, @RequestParam double price) throws CouponSystemException {
        return customerService.getAllCustomerCouponByMaxPrice(token,price);
    }

    @GetMapping("/details")
    public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return customerService.getCustomerDetails(token);
    }
}
