package com.jb.couponsystem.security;

import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.exception.ErrMsg;
import com.jb.couponsystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {

    private final AdminServiceImpl adminService;
    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;

    public LoginResponse login(String email, String password, ClientType clientType) throws CouponSystemException {
        LoginResponse loginResponse = null;
        switch (clientType) {
            case ADMINISTRATOR:
                loginResponse = adminService.login(email, password);
                break;
            case COMPANY:
                loginResponse = companyService.login(email, password);
                break;
            case CUSTOMER:
                loginResponse = customerService.login(email, password);
                break;
            default:
                throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_WRONG);
        }
        return loginResponse;
    }


}
