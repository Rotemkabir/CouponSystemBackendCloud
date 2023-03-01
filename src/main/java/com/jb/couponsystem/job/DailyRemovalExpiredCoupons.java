package com.jb.couponsystem.job;

import com.jb.couponsystem.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class DailyRemovalExpiredCoupons {
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000*60*60*24, initialDelay = 1000 * 60)
    public void expired() {
        couponRepository.deleteAllByEndDateBefore(Date.valueOf(LocalDate.now()));
    }
}
