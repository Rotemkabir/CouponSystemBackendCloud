package com.jb.couponsystem.repos;

import com.jb.couponsystem.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmailAndIdNot(String email, int id);
    boolean existsByNameOrEmail(String name, String email);

    boolean existsByEmail(String email);

    Optional<Company> findByEmailAndPassword(String email, String password);

    @Query(value = "select * from companies where id = ?", nativeQuery = true)
    Company getDetails(int companyId);


}
