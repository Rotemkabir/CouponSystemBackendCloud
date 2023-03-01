package com.jb.couponsystem.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
    @Singular
    private List<Coupon> coupons;

}
