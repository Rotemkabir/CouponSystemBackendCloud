package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    void addCompany(UUID token,Company company) throws CouponSystemException;
    void updateCompany(UUID token,int companyId, Company company) throws CouponSystemException;
    void deleteCompany(UUID token,int companyId) throws CouponSystemException;
    List<Company> getAllCompanies(UUID token) throws CouponSystemException;
    Company getSingleCompany(UUID token,int companyId) throws CouponSystemException;
    void addCustomer(UUID token,Customer customer) throws CouponSystemException;
    void updateCustomer(UUID token,int customerId, Customer customer) throws CouponSystemException;
    void deleteCustomer(UUID token,int customerId) throws CouponSystemException;
    List<Customer>getAllCustomers(UUID token) throws CouponSystemException;
    Customer getSingleCustomer(UUID token,int customerId) throws CouponSystemException;




}
