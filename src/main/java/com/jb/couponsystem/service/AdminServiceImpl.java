package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.exception.ErrMsg;
import com.jb.couponsystem.security.Information;
import com.jb.couponsystem.security.LoginResponse;
import com.jb.couponsystem.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    @Value(value = "${email}")
    private String email;
    @Value(value = "${password}")
    private String password;

    private final TokenManager tokenManager;

    public void isAdmin(UUID token)throws CouponSystemException{
        tokenManager.isAuthorize(token);
    }

    @Override
    public LoginResponse login(String email, String password) throws CouponSystemException {
        if (!(this.email.equalsIgnoreCase(email) && this.password.equalsIgnoreCase(password))) {
            throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_WRONG);
        }
        Information information = Information.builder()
                .id(-1)
                .email(email)
                .clientType(ClientType.ADMINISTRATOR)
                .expirationTime(LocalDateTime.now().plusMinutes(30))
                .build();
        UUID token = tokenManager.addToken(information);
        return new LoginResponse(token, information.getId(), information.getEmail(), "Admin", ClientType.ADMINISTRATOR);
    }

    @Override
    public void addCompany(UUID token, Company company) throws CouponSystemException {
        isAdmin(token);
        if (this.companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponSystemException(ErrMsg.NAME_OR_EMAIL_ALREADY_EXIST);
        }
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(UUID token,int companyId, Company company) throws CouponSystemException {
        isAdmin(token);
        Company companyFromDB = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        if (companyId != company.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_ID_OR_NAME);
        }
        if (!(companyFromDB.getName().equalsIgnoreCase(company.getName()))) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_ID_OR_NAME);
        }
        if (this.companyRepository.existsByEmailAndIdNot(company.getEmail(), companyId)) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }
        company.setId(companyId);
        company.getCoupons().forEach(c -> c.setCompany(company));
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(UUID token,int companyId) throws CouponSystemException {
        isAdmin(token);
        if (!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        this.couponRepository.deletePurchaseCouponByCompanyId(companyId);
        this.companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies(UUID token) throws CouponSystemException {
        isAdmin(token);
        return this.companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(UUID token,int companyId) throws CouponSystemException {
        isAdmin(token);
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
    }

    @Override
    public void addCustomer(UUID token,Customer customer) throws CouponSystemException {
        isAdmin(token);
        if (this.customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(UUID token,int customerId, Customer customer) throws CouponSystemException {
        isAdmin(token);
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        if (customerId != customer.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_CUSTOMER_ID);
        }
        if (this.customerRepository.existsByEmailAndIdNot(customer.getEmail(), customerId)) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }
        customer.setId(customerId);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(UUID token,int customerId) throws CouponSystemException {
        isAdmin(token);
        if (!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        this.couponRepository.deletePurchaseCouponByCustomerId(customerId);
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers(UUID token) throws CouponSystemException {
        isAdmin(token);
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(UUID token,int customerId) throws CouponSystemException {
        isAdmin(token);
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
    }

}
