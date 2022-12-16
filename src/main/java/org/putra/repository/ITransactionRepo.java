package org.putra.repository;

import org.putra.entity.Customer;
import org.putra.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ITransactionRepo extends JpaRepository<Transaction, String> {
    @Query(value = "SELECT * FROM transaction", nativeQuery = true)
    List<Transaction> findAllTransaction();

    @Query(value = "SELECT * FROM transaction t WHERE t.customer = ?1 AND t.bookedDate = ?2", nativeQuery = true)
    Transaction findByCustomerAndBookedDate(Customer customer, Date bookedDate);
}
