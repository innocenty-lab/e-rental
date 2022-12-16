package org.putra.service;

import org.putra.entity.Car;

import java.util.List;

public interface ICarServ {
    Car insertCar(Car car) throws Exception;
    List<Car> showAllCar() throws Exception;
    Car editCar(Car car, String policeNumber) throws Exception;
    void removeCar(String policeNumber) throws Exception;
}
