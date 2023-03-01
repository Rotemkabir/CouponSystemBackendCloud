package com.jb.couponsystem.repos;

import com.jb.couponsystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, int id);
    Optional<Customer> findByEmailAndPassword(String email, String password);
    @Query(value = "select * from customers where id = ?", nativeQuery = true)
    Customer getDetails(int customerId);
}
