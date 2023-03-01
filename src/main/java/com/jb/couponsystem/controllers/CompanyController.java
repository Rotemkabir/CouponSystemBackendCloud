package com.jb.couponsystem.controllers;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        return companyService.addCoupon(token, coupon);
    }


    @PutMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Coupon updateCoupon(@RequestHeader("Authorization") UUID token,@PathVariable int couponId,@RequestBody Coupon coupon) throws CouponSystemException {
        return companyService.updateCoupon(token,couponId,coupon);
    }

    @DeleteMapping("/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        companyService.deleteCoupon(token, couponId);
    }

    @GetMapping("coupons/{couponId}")
    public Coupon getSingleCoupon(@RequestHeader("Authorization") UUID token,@PathVariable int couponId) throws CouponSystemException {
       return companyService.getSingleCoupon(token,couponId);
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCouponsByCompanyId(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return companyService.getAllCouponsByCompanyId(token);
    }

    @GetMapping("/coupons/by/category")
    public List<Coupon> getAllCompanyCouponsBySpecificCategory(@RequestHeader("Authorization") UUID token, @RequestParam String category) throws CouponSystemException {
        return companyService.getAllCompanyCouponsBySpecificCategory(token, category);
    }

    @GetMapping("/coupons/price/under")
    public List<Coupon> getAllCompanyCouponByMaxPrice(@RequestHeader("Authorization") UUID token, @RequestParam double price) throws CouponSystemException {
        return companyService.getAllCompanyCouponByMaxPrice(token, price);
    }

    @GetMapping("/details")
    public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return companyService.getCompanyDetails(token);
    }
}
