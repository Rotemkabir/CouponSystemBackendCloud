//package com.jb.couponsystem.clr;
//
//import com.jb.couponsystem.beans.Category;
//import com.jb.couponsystem.beans.Company;
//import com.jb.couponsystem.beans.Coupon;
//import com.jb.couponsystem.exception.CouponSystemException;
//import com.jb.couponsystem.exception.ErrMsg;
//import com.jb.couponsystem.repos.CouponRepository;
//import com.jb.couponsystem.utils.Colors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Arrays;
//
//@Component
//@Order(4)
//public class ControllerCompanyTest implements CommandLineRunner {
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private CouponRepository couponRepository;
//
//    @Value("${URL2}")
//    private String URL;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   COMPANY   " + Colors.RESET);
//        System.out.println(Colors.CYAN + "Add coupon" + Colors.RESET);
//        addCoupon();
//        System.out.println(Colors.CYAN + "Update coupon" + Colors.RESET);
//        updateCoupon();
//        System.out.println(Colors.CYAN + "Delete coupon" + Colors.RESET);
//        deleteCoupon();
//        System.out.println(Colors.CYAN + "Get single coupon" + Colors.RESET);
//        getSingleCoupon();
//        System.out.println(Colors.CYAN + "Get All Coupons By Company Id" + Colors.RESET);
//        getAllCouponsByCompanyId();
//        System.out.println(Colors.CYAN + "Get All Company Coupons By Specific Category" + Colors.RESET);
//        getAllCompanyCouponsBySpecificCategory();
//        System.out.println(Colors.CYAN + "Get All Company Coupon By Max Price" + Colors.RESET);
//        getAllCompanyCouponByMaxPrice();
//        System.out.println(Colors.CYAN + "Get Company Details" + Colors.RESET);
//        getCompanyDetails();
//    }
//
//    public void addCoupon() {
//        Coupon coupon10 = Coupon.builder()
//                .category(Category.VACATION)
//                .title("11%")
//                .description("coupon6")
//                .startDate(Date.valueOf(LocalDate.now().minusWeeks(40)))
//                .endDate(Date.valueOf(LocalDate.now().plusMonths(4)))
//                .amount(666)
//                .price(400)
//                .image("coupon6")
//                .build();
//        ResponseEntity<String> res = restTemplate.postForEntity(URL + "/1/coupons", coupon10, String.class);
//        System.out.println(res.getStatusCode());
//    }
//
//    public void updateCoupon() {
//        try {
//            Coupon c1 = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
//            c1.setAmount(17);
//            restTemplate.put(URL + "/coupons/1", c1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteCoupon() {
//        restTemplate.delete(URL + "/2/3");
//    }
//
//    public void getSingleCoupon() {
//        Coupon coupon = restTemplate.getForObject(URL + "/coupons/4", Coupon.class);
//        System.out.println(coupon);
//    }
//
//    public void getAllCouponsByCompanyId() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/1/coupons", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getAllCompanyCouponsBySpecificCategory() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/3/coupons/by/category?category=RESTAURANT", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getAllCompanyCouponByMaxPrice() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/1/coupons/price/under?price=400", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getCompanyDetails() {
//        Company company = restTemplate.getForObject(URL + "/2", Company.class);
//        System.out.println(company);
//    }
//}
