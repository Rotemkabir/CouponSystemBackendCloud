package com.jb.couponsystem.service;

import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.exception.ErrMsg;
import com.jb.couponsystem.security.Information;
import com.jb.couponsystem.security.LoginResponse;
import com.jb.couponsystem.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private final TokenManager tokenManager;

    public void isCompany(UUID token) throws CouponSystemException {
        tokenManager.isAuthorize(token);
    }

    @Override
    public LoginResponse login(String email, String password) throws CouponSystemException {
        Company company = companyRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_WRONG));
        System.out.println(company);
        Information information = Information.builder()
                .id(company.getId())
                .email(company.getEmail())
                .clientType(ClientType.COMPANY)
                .expirationTime(LocalDateTime.now().plusMinutes(30))
                .build();
        System.out.println(information);
        UUID token = tokenManager.addToken(information);
        System.out.println(token);
        return new LoginResponse(token, information.getId(), information.getEmail(), company.getName(), ClientType.COMPANY);
    }

    @Override
    public Coupon addCoupon(UUID token, Coupon coupon) throws CouponSystemException {
        isCompany(token);
        int companyId = tokenManager.getUserId(token);
        coupon.setCompany(companyRepository.findById(companyId).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_EXIST)));
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)){
            throw new CouponSystemException(ErrMsg.COUPON_TITLE_ALREADY_EXIST);
        }        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(UUID token, int couponId, Coupon coupon) throws CouponSystemException {
        isCompany(token);
        Company companyFromDb = companyRepository.findById(tokenManager.getUserId(token)).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
        Coupon couponFromDb = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));

        if (couponId != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COUPON_OR_COMPANY_ID);
        }
        if (!couponFromDb.getTitle().equalsIgnoreCase(coupon.getTitle())) {
            if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyFromDb.getId())){
                throw new CouponSystemException(ErrMsg.COUPON_TITLE_ALREADY_EXIST);
            }
        }
        coupon.setCompany(companyFromDb);
        coupon.setId(couponId);
        return couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(UUID token, int couponId) throws CouponSystemException {
        isCompany(token);
        if (!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        couponRepository.deletePurchaseCouponByCouponId(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon getSingleCoupon(UUID token, int couponId) throws CouponSystemException {
        isCompany(token);
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
    }

    @Override
    public List<Coupon> getAllCouponsByCompanyId(UUID token) throws CouponSystemException {
        isCompany(token);
        int companyId = tokenManager.getUserId(token);
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsBySpecificCategory(UUID token, String category) throws CouponSystemException {
        isCompany(token);
        int companyId = tokenManager.getUserId(token);
        return couponRepository.findCompanyCouponsBySpecificCategory(companyId, category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponByMaxPrice(UUID token, double price) throws CouponSystemException {
        isCompany(token);
        int companyId = tokenManager.getUserId(token);
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, price);
    }

    @Override
    public Company getCompanyDetails(UUID token) throws CouponSystemException {
        isCompany(token);
        int companyId = tokenManager.getUserId(token);
        return companyRepository.getDetails(companyId);
    }
}
