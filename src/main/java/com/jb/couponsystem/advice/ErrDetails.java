package com.jb.couponsystem.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrDetails {

    private final String key = "coupon system";
    private String value;
}
