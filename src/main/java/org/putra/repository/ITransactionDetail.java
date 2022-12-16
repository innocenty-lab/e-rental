package org.putra.repository;

import org.putra.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionDetail extends JpaRepository<TransactionDetail, String> {

}
