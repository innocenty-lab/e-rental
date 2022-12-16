package org.putra.repository;

import org.putra.entity.Customer;
import org.putra.entity.Price;
import org.putra.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE mst_customer c SET c.customer_nik = ?1", nativeQuery = true)
    int updateCustomerByNik(String customerNik);

    @Query(value = "SELECT * FROM mst_customer", nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "SELECT * FROM mst_customer c WHERE c.customer_nik = ?1", nativeQuery = true)
    Customer findByCustomerNik(String customerNik);
}
