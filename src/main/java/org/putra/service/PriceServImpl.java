package org.putra.service;

import org.putra.entity.Car;
import org.putra.entity.Price;
import org.putra.exception.EntityExistException;
import org.putra.exception.NotFoundException;
import org.putra.repository.ICarRepo;
import org.putra.repository.IPriceRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriceServImpl implements IPriceServ {
    private final IPriceRepo iPriceRepo;
    private final ICarRepo iCarRepo;

    public PriceServImpl(IPriceRepo iPriceRepo, ICarRepo iCarRepo) {
        this.iPriceRepo = iPriceRepo;
        this.iCarRepo = iCarRepo;
    }

    @Transactional
    @Override
    public Price insertPrice(Price price) throws Exception {
        try {
            Car existingCar = iCarRepo.findByPoliceNumber(price.getCar().getPoliceNumber());

            if (existingCar == null) {
                throw new NotFoundException("Insert price failed because car police number is not found");
            }

            price.setCar(existingCar);

            return iPriceRepo.save(price);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public List<Price> showAllPrice() throws Exception {
        List<Price> priceList = iPriceRepo.findAllPrice();

        if (priceList.isEmpty()) {
            throw new NotFoundException();
        }

        return priceList;
    }

    @Transactional
    @Override
    public Price editPrice(Price price, String priceId) throws Exception {
        Price existingPrice = iPriceRepo.findByPriceId(priceId);

        if (existingPrice != null) {
            price.setPriceId(existingPrice.getPriceId());
            return iPriceRepo.save(price);
        } else {
            throw new NotFoundException("Edit price failed because price id is not found");
        }
    }

    @Transactional
    @Override
    public void removePrice(String priceId) throws Exception {
        try {
            iPriceRepo.deleteByPriceId(priceId);
        } catch (NotFoundException e) {
            throw new NotFoundException("Remove price failed because price id is not found");
        }
    }
}
