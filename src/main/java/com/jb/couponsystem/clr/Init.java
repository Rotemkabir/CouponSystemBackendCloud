package com.jb.couponsystem.clr;

import com.jb.couponsystem.beans.Category;
import com.jb.couponsystem.beans.Company;
import com.jb.couponsystem.beans.Coupon;
import com.jb.couponsystem.beans.Customer;
import com.jb.couponsystem.repos.CompanyRepository;
import com.jb.couponsystem.repos.CouponRepository;
import com.jb.couponsystem.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        Coupon coupon1 = Coupon.builder()
                .category(Category.FOOD)
                .title("10%")
                .description("hamburger")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(30)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(4)))
                .amount(111)
                .price(100)
                .image("https://i.imgur.com/WCjLcus.jpg")
                .build();
        Coupon coupon2 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("20%")
                .description("Home appliances")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(20)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(6)))
                .amount(222)
                .price(200)
                .image("https://i.imgur.com/vsI6Rjj.jpg")
                .build();
        Coupon coupon3 = Coupon.builder()
                .category(Category.RESTAURANT)
                .title("30%")
                .description("Dinner for 6")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(80)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(2)))
                .amount(333)
                .price(300)
                .image("https://i.imgur.com/hXUS86J.jpg")
                .build();
        Coupon coupon4 = Coupon.builder()
                .category(Category.VACATION)
                .title("40%")
                .description("hotel")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(40)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(4)))
                .amount(444)
                .price(400)
                .image("https://i.imgur.com/u0dwy0J.jpg")
                .build();
        Coupon coupon5 = Coupon.builder()
                .category(Category.FOOD)
                .title("50%")
                .description("pizza")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(50)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
                .amount(555)
                .price(500)
                .image("https://i.imgur.com/E7I3v0C.jpg")
                .build();
        Coupon coupon6 = Coupon.builder()
                .category(Category.VACATION)
                .title("60%")
                .description("flight")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(60)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
                .amount(666)
                .price(600)
                .image("https://i.imgur.com/LkkoBSe.jpg")
                .build();
        Coupon coupon7 = Coupon.builder()
                .category(Category.RESTAURANT)
                .title("70%")
                .description("Lunch for 4")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(70)))
                .endDate(Date.valueOf(LocalDate.now().plusMonths(5)))
                .amount(770)
                .price(700)
                .image("https://i.imgur.com/4EDirGk.jpg")
                .build();

        Company company1 = Company.builder()
                .name("BBB")
                .email("bbb@bbb.com")
                .password("bbbb")
                .coupons(List.of(coupon1, coupon3, coupon7))
                .build();
        Company company2 = Company.builder()
                .name("Electric")
                .email("electric@electric.com")
                .password("electric")
                .coupons(List.of(coupon2))
                .build();
        Company company3 = Company.builder()
                .name("Pasta basta")
                .email("pasta@pasta.com")
                .password("pasta")
                .coupons(List.of(coupon5, coupon6))
                .build();
        Company company4 = Company.builder()
                .name("fattal hotels")
                .email("fattal@fattal.com")
                .password("fattal")
                .coupons(List.of(coupon4))
                .build();

        Customer customer1 = Customer.builder()
                .firstName("kobi")
                .lastName("shasha")
                .email("kobi@kobi.com")
                .password("kobi")
                .coupons(List.of(coupon1, coupon5))
                .build();
        Customer customer2 = Customer.builder()
                .firstName("rotem")
                .lastName("kabir")
                .email("rotem@rotem.com")
                .password("rotem")
                .coupons(List.of(coupon4, coupon1, coupon3))
                .build();
        Customer customer3 = Customer.builder()
                .firstName("menachem")
                .lastName("bussi")
                .email("menachem@menachem.com")
                .password("menachem")
                .coupons(List.of(coupon3, coupon2))
                .build();
        Customer customer4 = Customer.builder()
                .firstName("ella")
                .lastName("adler")
                .email("ella@ella.com")
                .password("ella")
                .coupons(List.of(coupon7, coupon6))
                .build();

        coupon1.setCompany(company1);
        coupon3.setCompany(company1);
        coupon2.setCompany(company2);
        coupon5.setCompany(company3);
        coupon6.setCompany(company3);
        coupon4.setCompany(company4);
        coupon7.setCompany(company1);

        companyRepository.saveAll(List.of(company1, company2, company3, company4));
        couponRepository.saveAll(List.of(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7));
        customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4));

    }
}
