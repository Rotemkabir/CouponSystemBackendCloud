package com.jb.couponsystem.exception;

import lombok.Getter;

@Getter
public enum ErrMsg {

    NAME_OR_EMAIL_ALREADY_EXIST("name or email already exist"),
    EMAIL_ALREADY_EXIST("email already exist"),
    ID_NOT_EXIST("this id are no exist"),
    EMAIL_OR_PASSWORD_WRONG("email or password are wrong"),
    COUPON_TITLE_ALREADY_EXIST("coupon title already exist"),
    CANNOT_UPDATE_CUSTOMER_ID("cannot update customer id"),
    CANNOT_UPDATE_COMPANY_ID_OR_NAME("cannot update company id or name"),
    CANNOT_UPDATE_COUPON_OR_COMPANY_ID("cannot update coupon id or company id"),
    COUPON_ALREADY_PURCHASE("cannot buy this coupon. already purchase !"),
    NO_MORE_FROM_SELECTED_COUPON("cannot buy this coupon.no more of the selected coupon !"),
    COUPON_EXPIRED("This coupon has expired"),
    NOT_VALID_TOKEN("not valid token"),

    UN_AUTHORIZE_ACTION("unAuthorize action");

    private String massage;

    ErrMsg(String massage) {
        this.massage = massage;
    }
}
