package com.jb.couponsystem.exception;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getMassage());
    }
}
