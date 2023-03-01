package com.jb.couponsystem.repos;

import com.jb.couponsystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByCompanyId(int companyId);
    void deleteAllByEndDateBefore(Date date);
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);
    boolean existsByTitleAndCompanyId(String title, int companyId);

    @Query(value = "select * from coupons where id in (select coupons_id from customers_coupons where customer_id = ?)", nativeQuery = true)
    List<Coupon> findPurchasedCouponsByCustomerId(int customerId);

    @Query(value = "select * from coupons where price <= ? and id in (select coupons_id from customers_coupons where customer_id = ?)", nativeQuery = true)
    List<Coupon> findCustomerCouponsByMaxPrice(double price, int customerId);

    @Query(value = "select * from coupons where company_id = ? and category = ?", nativeQuery = true)
    List<Coupon> findCompanyCouponsBySpecificCategory(int companyId, String category);

    @Query(value = "select * from coupons where category = ? and id in (select coupons_id from customers_coupons where customer_id = ?)", nativeQuery = true)
    List<Coupon> findCustomerCouponsBySpecificCategory(String category, int customerId);

    @Transactional
    @Modifying
    @Query(value = "delete from coupons where company_id = ?", nativeQuery = true)
    void deletePurchaseCouponByCompanyId(int companyId);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons where customer_id = ?", nativeQuery = true)
    void deletePurchaseCouponByCustomerId(int customerId);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons where coupons_id = ?", nativeQuery = true)
    void deletePurchaseCouponByCouponId(int couponId);

    @Query(value = "select case when exists (select * from customers_coupons where customer_id = ? and coupons_id = ?) then \"true\" else \"false\" end", nativeQuery = true)
    boolean isCouponPurchasedByCustomer(int customerId, int couponId);

    @Transactional
    @Modifying
    @Query(value = "insert into customers_coupons (customer_id, coupons_id) values (?1,?2)", nativeQuery = true)
    void purchaseCoupon(int customerId, int couponId);


}
