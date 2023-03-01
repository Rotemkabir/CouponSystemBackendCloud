package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.exception.ErrMsg;
import com.jb.couponsystem.security.Information;
import com.jb.couponsystem.security.LoginResponse;
import com.jb.couponsystem.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final TokenManager tokenManager;

    public void isCustomer(UUID token) throws CouponSystemException {
        tokenManager.isAuthorize(token);
    }

    @Override
    public LoginResponse login(String email, String password) throws CouponSystemException {
        Customer customer = customerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(()->new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_WRONG));
        Information information = Information.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .clientType(ClientType.CUSTOMER)
                .expirationTime(LocalDateTime.now().plusMinutes(30))
                .build();
        UUID token = tokenManager.addToken(information);
        return new LoginResponse(token, information.getId(), information.getEmail(), customer.getFirstName(), ClientType.CUSTOMER);
    }

    @Override
    public Coupon purchaseCoupon(UUID token, int couponId) throws CouponSystemException {
        isCustomer(token);
        int customerId = tokenManager.getUserId(token);
        Coupon couponFromDB = couponRepository.findById(couponId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_EXIST));
        if (couponRepository.isCouponPurchasedByCustomer(customerId, couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASE);
        }
        if (couponFromDB.getAmount() <= 0) {
            throw new CouponSystemException(ErrMsg.NO_MORE_FROM_SELECTED_COUPON);
        }
        if (couponFromDB.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        couponRepository.purchaseCoupon(customerId, couponId);
        couponFromDB.setAmount(couponFromDB.getAmount() - 1);
        return  couponRepository.saveAndFlush(couponFromDB);
    }

    @Override
    public Coupon getSingleCoupon(UUID token,int couponId) throws CouponSystemException {
        isCustomer(token);
        return couponRepository.findById(couponId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_EXIST));
    }

    @Override
    public List<Coupon> getCustomerCouponsById(UUID token) throws CouponSystemException {
        isCustomer(token);
        int customerId = tokenManager.getUserId(token);
        return couponRepository.findPurchasedCouponsByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getAllCustomerCouponsBySpecificCategory(UUID token,String category) throws CouponSystemException {
        isCustomer(token);
        int customerId = tokenManager.getUserId(token);
        return couponRepository.findCustomerCouponsBySpecificCategory(category, customerId);
    }

    @Override
    public List<Coupon> getAllCustomerCouponByMaxPrice(UUID token,double price) throws CouponSystemException {
        isCustomer(token);
        int customerId = tokenManager.getUserId(token);
        return couponRepository.findCustomerCouponsByMaxPrice(price, customerId);
    }

    @Override
    public Customer getCustomerDetails(UUID token) throws CouponSystemException {
        isCustomer(token);
        int customerId = tokenManager.getUserId(token);
        return customerRepository.getDetails(customerId);
    }

    @Override
    public List<Coupon> getAllCoupons(UUID token) throws CouponSystemException {
        isCustomer(token);
        return this.couponRepository.findAll();
    }
}
