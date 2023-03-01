//package com.jb.couponsystem.clr;
//
//import com.jb.couponsystem.beans.Category;
//import com.jb.couponsystem.beans.Company;
//import com.jb.couponsystem.beans.Coupon;
//import com.jb.couponsystem.beans.Customer;
//import com.jb.couponsystem.exception.CouponSystemException;
//import com.jb.couponsystem.service.AdminServiceImpl;
//import com.jb.couponsystem.service.CompanyServiceImpl;
//import com.jb.couponsystem.service.CustomerServiceImpl;
//import com.jb.couponsystem.utils.Colors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.time.LocalDate;
//
//@Component
//@Order(2)
//public class ServiceTesting implements CommandLineRunner {
//
//    @Autowired
//    private AdminServiceImpl adminService;
//    @Autowired
//    private CompanyServiceImpl companyService;
//    @Autowired
//    private CustomerServiceImpl customerService;
//
//    @Override
//    public void run(String... args) {
//        System.out.println();
//        System.out.println(Colors.YELLOW_BACKGROUND + "" + Colors.BLACK + "---SERVICE TESTING---" + Colors.RESET);
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   ADMIN   " + Colors.RESET);
//        try {
//            System.out.println(Colors.RED + "Login admin - FAIL email/password" + Colors.RESET);
//            adminService.login("admi@admi.com", "admin");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Login admin - SUCCESS" + Colors.RESET);
//            adminService.login("admin@admin.com", "admin");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.RED + "Add new company - FAIL name/email " + Colors.RESET);
//            Company company1 = Company.builder()
//                    .name("BBB")
//                    .email("bbb@bbb.com")
//                    .password("bbb")
//                    .build();
//            adminService.addCompany(company1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Add new company - SUCCESS " + Colors.RESET);
//            Company company2 = Company.builder()
//                    .name("coca-cola")
//                    .email("coca@cola.com")
//                    .password("ccc")
//                    .build();
//            adminService.addCompany(company2);
//            adminService.getAllCompanies().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.RED + "Update exist company - FAIL name/id" + Colors.RESET);
//            Company c1 = adminService.getSingleCompany(2);
//            c1.setName("company90");
//            adminService.updateCompany(c1.getId(), c1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Update exist company - SUCCESS" + Colors.RESET);
//            Company c2 = adminService.getSingleCompany(2);
//            c2.setPassword("22222");
//            adminService.updateCompany(c2.getId(), c2);
//            adminService.getAllCompanies().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Delete company and company purchase coupon" + Colors.RESET);
//            adminService.deleteCompany(5);
//            adminService.getAllCompanies().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Colors.GREEN + "get all companies" + Colors.RESET);
//        adminService.getAllCompanies().forEach(System.out::println);
//        try {
//            System.out.println(Colors.GREEN + "Get single company" + Colors.RESET);
//            System.out.println(adminService.getSingleCompany(3));
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.RED + "Add new customer - FAIL email" + Colors.RESET);
//            Customer customer1 = Customer.builder()
//                    .firstName("harel")
//                    .lastName("kabir")
//                    .email("rotem@rotem.com")
//                    .password("harel")
//                    .build();
//            adminService.addCustomer(customer1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Add new customer - SUCCESS " + Colors.RESET);
//            Customer customer2 = Customer.builder()
//                    .firstName("harel")
//                    .lastName("kabir")
//                    .email("harel@harel.com")
//                    .password("harel")
//                    .build();
//            adminService.addCustomer(customer2);
//            adminService.getAllCustomers().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.RED + "Update exist customer - FAIL id" + Colors.RESET);
//            Customer s1 = adminService.getSingleCustomer(2);
//            s1.setId(340);
//            adminService.updateCustomer(s1.getId(), s1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Update exist customer - SUCCESS" + Colors.RESET);
//            Customer s1 = adminService.getSingleCustomer(2);
//            s1.setPassword("340");
//            adminService.updateCustomer(s1.getId(), s1);
//            adminService.getAllCustomers().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Delete customer and customer purchase coupon" + Colors.RESET);
//            adminService.deleteCustomer(4);
//            adminService.getAllCustomers().forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Colors.GREEN + "get all customers" + Colors.RESET);
//        adminService.getAllCustomers().forEach(System.out::println);
//        try {
//            System.out.println(Colors.GREEN + "Get single customer" + Colors.RESET);
//            System.out.println(adminService.getSingleCustomer(3));
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   COMPANY   " + Colors.RESET);
//        try {
//            System.out.println(Colors.RED + "Login company - FAIL email/password" + Colors.RESET);
//            companyService.login("cc@cc.com", "cc");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Login company - SUCCESS" + Colors.RESET);
//            companyService.login("bbb@bbb.com", "bbb");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.RED + "Add new coupon - FAIL title" + Colors.RESET);
//            Coupon p1 = Coupon.builder()
//                    .category(Category.ELECTRICITY)
//                    .title("50%")
//                    .description("coupon6")
//                    .company(adminService.getSingleCompany(1))
//                    .startDate(Date.valueOf(LocalDate.now().minusWeeks(50)))
//                    .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
//                    .amount(666)
//                    .price(600)
//                    .image("coupon6")
//                    .build();
//            companyService.addCoupon(1, p1);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Add new coupon - SUCCESS" + Colors.RESET);
//            Coupon p2 = Coupon.builder()
//                    .category(Category.ELECTRICITY)
//                    .title("60%")
//                    .description("coupon6")
//                    .company(adminService.getSingleCompany(1))
//                    .startDate(Date.valueOf(LocalDate.now().minusWeeks(50)))
//                    .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
//                    .amount(666)
//                    .price(600)
//                    .image("coupon6")
//                    .build();
//            companyService.addCoupon(1, p2);
//            companyService.getAllCouponsByCompanyId(1).forEach(System.out::println);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
////        try {
////            System.out.println(Colors.RED + "Update exist coupon - FAIL coupon id/company id" + Colors.RESET);
////            Coupon p3 = companyService.getSingleCoupon(5);
////            p3.setId(3);
////            companyService.updateCoupon(4, p3,5);
////        } catch (CouponSystemException e) {
////            System.out.println(e.getMessage());
////        }
//
//        try {
//            System.out.println(Colors.RED + "Update exist coupon - FAIL coupon id/company id" + Colors.RESET);
//            Coupon p3 = companyService.getSingleCoupon(5);
//            p3.setId(3);
//            companyService.updateCoupon(5, p3);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
////        try {
////            System.out.println(Colors.GREEN + "Update exist coupon - SUCCESS" + Colors.RESET);
////            Coupon p4 = companyService.getSingleCoupon(5);
////            p4.setAmount(110);
////            companyService.updateCoupon(4,  p4,5);
////            System.out.println(companyService.getSingleCoupon(5));
////        } catch (CouponSystemException e) {
////            System.out.println(e.getMessage());
////        }
//
//        try {
//            System.out.println(Colors.GREEN + "Update exist coupon - SUCCESS" + Colors.RESET);
//            Coupon p4 = companyService.getSingleCoupon(5);
//            p4.setAmount(110);
//            companyService.updateCoupon(5,  p4);
//            System.out.println(companyService.getSingleCoupon(5));
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(Colors.GREEN + "Delete coupon and coupon purchase" + Colors.RESET);
//        try {
//            companyService.deleteCoupon(4, 5);
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Colors.GREEN + "Get all coupons by company id" + Colors.RESET);
//        companyService.getAllCouponsByCompanyId(2).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get all company coupons by specific category" + Colors.RESET);
//        companyService.getAllCompanyCouponsBySpecificCategory(1, String.valueOf(Category.FOOD)).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get all company coupons by max price" + Colors.RESET);
//        companyService.getAllCompanyCouponByMaxPrice(2, 800.0).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get details company" + Colors.RESET);
//        System.out.println(companyService.getCompanyDetails(1));
//        System.out.println(Colors.YELLOW_BACKGROUND_BRIGHT + "" + Colors.BLACK + "   CUSTOMER   " + Colors.RESET);
//        try {
//            System.out.println(Colors.RED + "Login customer - FAIL email/password" + Colors.RESET);
//            customerService.login("kob@kob.com", "cc");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Login customer - SUCCESS" + Colors.RESET);
//            customerService.login("kobi@kobi.com", "kobi");
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(Colors.GREEN + "Purchase coupon - SUCCESS" + Colors.RESET);
//            Coupon p5 = customerService.getSingleCoupon(4);
//            customerService.purchaseCoupon(1, p5.getId());
//        } catch (CouponSystemException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Colors.GREEN + "All customer coupons" + Colors.RESET);
//        customerService.getCustomerCouponsById(1).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get all customer coupons by specific category" + Colors.RESET);
//        customerService.getAllCustomerCouponsBySpecificCategory(String.valueOf(Category.FOOD), 1).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get all customer coupons by max price" + Colors.RESET);
//        customerService.getAllCustomerCouponByMaxPrice(1000.0, 3).forEach(System.out::println);
//        System.out.println(Colors.GREEN + "Get details customer" + Colors.RESET);
//        System.out.println(customerService.getCustomerDetails(1));
//        System.out.println();
//    }
//}
