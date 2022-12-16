package org.putra.service;

import org.putra.entity.Car;
import org.putra.exception.EntityExistException;
import org.putra.exception.NotFoundException;
import org.putra.repository.ICarRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServImpl implements ICarServ {

    private final ICarRepo iCarRepo;

    public CarServImpl(ICarRepo iCarRepo) {
        this.iCarRepo = iCarRepo;
    }

//    @Transactional
    @Override
    public Car insertCar(Car car) throws Exception {
        Car existingCar = iCarRepo.findByPoliceNumber(car.getPoliceNumber());

        if (existingCar != null) {
            throw new EntityExistException();
        }

        return iCarRepo.save(car);
    }

    @Override
    public List<Car> showAllCar() throws Exception {
        List<Car> carList = iCarRepo.findAllCar();

        if (carList.isEmpty()) {
            throw new NotFoundException();
        }

        return carList;
    }

    @Transactional
    @Override
    public Car editCar(Car car, String policeNumber) throws Exception {
        Car existingCar = iCarRepo.findByPoliceNumber(policeNumber);

        if (existingCar != null) {
            car.setPoliceNumber(existingCar.getPoliceNumber());
            return iCarRepo.save(car);
        } else {
            throw new NotFoundException("Edit car failed because police number is not found");
        }
    }

    @Transactional
    @Override
    public void removeCar(String policeNumber) throws Exception {
        try {
            iCarRepo.deleteByPoliceNumber(policeNumber);
        } catch (NotFoundException e) {
            throw new NotFoundException("Remove car failed because police number is not found");
        }
    }
}
