//package com.jb.couponsystem.clr;
//
//import com.jb.couponsystem.beans.Category;
//import com.jb.couponsystem.beans.Company;
//import com.jb.couponsystem.beans.Coupon;
//import com.jb.couponsystem.beans.Customer;
//import com.jb.couponsystem.exception.CouponSystemException;
//import com.jb.couponsystem.exception.ErrMsg;
//import com.jb.couponsystem.repos.CompanyRepository;
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
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@Order(3)
//public class ControllerAdminTest implements CommandLineRunner {
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private CouponRepository couponRepository;
//
//    @Value("${URL1}")
//    private String URL;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(Colors.YELLOW_BACKGROUND + "" + Colors.BLACK + "---REST TEMPLATE---" + Colors.RESET);
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   ADMIN   " + Colors.RESET);
//        System.out.println(Colors.CYAN + "Add company" + Colors.RESET);
//        addCompany();
//        System.out.println(Colors.CYAN + "Update company" + Colors.RESET);
//        updateCompany();
//        System.out.println(Colors.CYAN + "Get all companies" + Colors.RESET);
//        getAllCompanies();
//        System.out.println(Colors.CYAN + "Delete company" + Colors.RESET);
//        deleteCompany();
//        System.out.println(Colors.CYAN + "Get single company" + Colors.RESET);
//        getSingleCompany();
//        System.out.println(Colors.CYAN + "Add customer" + Colors.RESET);
//        addCustomer();
//        System.out.println(Colors.CYAN + "Update customer" + Colors.RESET);
//        updateCustomer();
//        System.out.println(Colors.CYAN + "Get all customers" + Colors.RESET);
//        getAllCustomers();
//        System.out.println(Colors.CYAN + "Delete customer" + Colors.RESET);
//        deleteCustomer();
//        System.out.println(Colors.CYAN + "Get single customer" + Colors.RESET);
//        getSingleCustomer();
//    }
//
//    public void addCompany() {
//        Coupon coupon6 = Coupon.builder()
//                .category(Category.VACATION)
//                .title("60%")
//                .description("coupon6")
//                .startDate(Date.valueOf(LocalDate.now().minusWeeks(40)))
//                .endDate(Date.valueOf(LocalDate.now().plusMonths(4)))
//                .amount(666)
//                .price(400)
//                .image("coupon6")
//                .build();
//        Coupon coupon7 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("70%")
//                .description("coupon7")
//                .startDate(Date.valueOf(LocalDate.now().minusWeeks(50)))
//                .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
//                .amount(770)
//                .price(77)
//                .image("coupon7")
//                .build();
//        Company company1 = Company.builder()
//                .name("PPP")
//                .email("ppp@bbb.com")
//                .password("bbb")
//                .coupons(List.of(coupon6, coupon7))
//                .build();
//        coupon6.setCompany(company1);
//        coupon7.setCompany(company1);
//        ResponseEntity<String> res = restTemplate.postForEntity(URL + "/companies", company1, String.class);
//        System.out.println(res.getStatusCode());
//    }
//
//    public void updateCompany() {
//        try {
//            Company c1 = companyRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
//            c1.setPassword("963");
//            restTemplate.put(URL + "/companies/1", c1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteCompany() {
//        restTemplate.delete(URL + "/companies/6");
//    }
//
//    public void getAllCompanies() {
//        Company[] companies = restTemplate.getForObject(URL + "/companies", Company[].class);
//        Arrays.asList(companies).forEach(System.out::println);
//    }
//
//    public void getSingleCompany() {
//        Company company = restTemplate.getForObject(URL + "/companies/4", Company.class);
//        System.out.println(company);
//    }
//
//    public void addCustomer() {
////        Coupon coupon8 = Coupon.builder()
////                .category(Category.VACATION)
////                .title("80%")
////                .description("coupon6")
////                .startDate(Date.valueOf(LocalDate.now().minusWeeks(40)))
////                .endDate(Date.valueOf(LocalDate.now().plusMonths(4)))
////                .amount(666)
////                .price(400)
////                .image("coupon8")
////                .build();
////        Coupon coupon9 = Coupon.builder()
////                .category(Category.FOOD)
////                .title("90%")
////                .description("coupon7")
////                .startDate(Date.valueOf(LocalDate.now().minusWeeks(50)))
////                .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
////                .amount(770)
////                .price(77)
////                .image("coupon9")
////                .build();
//        Customer customer1 = Customer.builder()
//                .firstName("robi")
//                .lastName("shasha")
//                .email("sh@shasha.com")
//                .password("shasha")
////                .coupons(List.of(coupon8, coupon9))
//                .build();
//        ResponseEntity<String> res = restTemplate.postForEntity(URL + "/customers", customer1, String.class);
//         System.out.println(res.getStatusCode());
//    }
//
//    public void updateCustomer() {
//        try {
//            Customer c1 = customerRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
//            c1.setPassword("963");
//            restTemplate.put(URL + "/customers/1", c1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void deleteCustomer() {
//        restTemplate.delete(URL + "/customers/6");
//    }
//
//    public void getAllCustomers() {
//        Customer[] customers = restTemplate.getForObject(URL + "/customers", Customer[].class);
//        Arrays.asList(customers).forEach(System.out::println);
//    }
//
//    public void getSingleCustomer() {
//        Customer customer = restTemplate.getForObject(URL + "/customers/2", Customer.class);
//        System.out.println(customer);
//    }
//
//}
//
