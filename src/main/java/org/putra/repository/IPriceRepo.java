package org.putra.repository;

import org.putra.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPriceRepo extends JpaRepository<Price, String> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM mst_price mp WHERE mp.price_id = ?1", nativeQuery = true)
    int deleteByPriceId(String priceId);

    @Query(value = "SELECT * FROM mst_price", nativeQuery = true)
    List<Price> findAllPrice();

    @Query(value = "SELECT * FROM mst_price p WHERE p.priceId = ?1", nativeQuery = true)
    Price findByPriceId(String priceId);
}
