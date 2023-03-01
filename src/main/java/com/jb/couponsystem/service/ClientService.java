package com.jb.couponsystem.service;

import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.repos.CompanyRepository;
import com.jb.couponsystem.repos.CouponRepository;
import com.jb.couponsystem.repos.CustomerRepository;
import com.jb.couponsystem.security.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract LoginResponse login(String email, String password) throws CouponSystemException;
}
