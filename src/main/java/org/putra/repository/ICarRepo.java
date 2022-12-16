package org.putra.repository;

import org.putra.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICarRepo extends JpaRepository<Car, String> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM mst_car c WHERE c.police_number = ?1", nativeQuery = true)
    int deleteByPoliceNumber(String policeNumber);

    @Query(value = "SELECT * FROM mst_car", nativeQuery = true)
    List<Car> findAllCar();

    @Query(value = "SELECT * FROM mst_car c WHERE c.police_number = ?1", nativeQuery = true)
    Car findByPoliceNumber(String policeNumber);


}
