package com.jb.couponsystem.controllers;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(token,company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(token,companyId, company);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponSystemException {
        adminService.deleteCompany(token,companyId);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return adminService.getAllCompanies(token);
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(token,companyId);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader("Authorization") UUID token,@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(token,customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId, @RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(token,customerId, customer);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponSystemException {
        adminService.deleteCustomer(token,customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        return adminService.getAllCustomers(token);
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(token,customerId);
    }

}
