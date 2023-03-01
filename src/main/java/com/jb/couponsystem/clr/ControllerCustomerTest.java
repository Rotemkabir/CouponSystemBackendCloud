//package com.jb.couponsystem.clr;
//
//import com.jb.couponsystem.beans.Coupon;
//import com.jb.couponsystem.beans.Customer;
//import com.jb.couponsystem.exception.CouponSystemException;
//import com.jb.couponsystem.exception.ErrMsg;
//import com.jb.couponsystem.repos.CouponRepository;
//import com.jb.couponsystem.repos.CustomerRepository;
//import com.jb.couponsystem.utils.Colors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
//@Component
//@Order(5)
//public class ControllerCustomerTest implements CommandLineRunner {
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private CouponRepository couponRepository;
//
//    @Value("${URL3}")
//    private String URL;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   CUSTOMER   " + Colors.RESET);
//        System.out.println(Colors.CYAN + "Purchase Coupon" + Colors.RESET);
//        purchaseCoupon();
//        System.out.println(Colors.CYAN + "Get Single Coupon" + Colors.RESET);
//        getSingleCoupon();
//        System.out.println(Colors.CYAN + "Get Customer Coupons By Id" + Colors.RESET);
//        getCustomerCouponsById();
//        System.out.println(Colors.CYAN + "Get All Customer Coupons By Specific Category" + Colors.RESET);
//        getAllCustomerCouponsBySpecificCategory();
//        System.out.println(Colors.CYAN + "Get All Customer Coupons By max price" + Colors.RESET);
//        getAllCustomerCouponByMaxPrice();
//        System.out.println(Colors.CYAN + "Get Customer details" + Colors.RESET);
//        getCustomerDetails();
//    }
//
//    public void purchaseCoupon() {
//        try {
//            Coupon coupon11 = couponRepository.findById(2).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
//            ResponseEntity<String> res = restTemplate.postForEntity(URL + "/2/coupons/2", coupon11, String.class);
//            System.out.println(res.getStatusCode());
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void getSingleCoupon() {
//        Coupon coupon = restTemplate.getForObject(URL + "/coupons/7", Coupon.class);
//        System.out.println(coupon);
//    }
//
//    public void getCustomerCouponsById() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/1/coupons", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getAllCustomerCouponsBySpecificCategory() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/1/coupons/by/category?category=FOOD", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getAllCustomerCouponByMaxPrice() {
//        Coupon[] coupons = restTemplate.getForObject(URL + "/1/coupons/price/under?price=400", Coupon[].class);
//        Arrays.asList(coupons).forEach(System.out::println);
//    }
//
//    public void getCustomerDetails() {
//        Customer customer = restTemplate.getForObject(URL + "/2", Customer.class);
//        System.out.println(customer);
//    }
//}
