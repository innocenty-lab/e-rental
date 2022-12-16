package org.putra.controller;

import org.modelmapper.ModelMapper;
import org.putra.constant.UrlMapping;
import org.putra.entity.Car;
import org.putra.entity.request.CarReq;
import org.putra.entity.response.SuccessResponse;
import org.putra.service.ICarServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.CAR_URL)
@Validated
public class CarController {

    private final ICarServ iCarServ;
    private final ModelMapper modelMapper;

    public CarController(ICarServ iCarServ, ModelMapper modelMapper) {
        this.iCarServ = iCarServ;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity insertCar(@Valid @RequestBody CarReq carReq) throws Exception {
        Car mappedCar = modelMapper.map(carReq, Car.class);
        Car newCar = iCarServ.insertCar(mappedCar);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Insert car success", newCar));
    }

    @DeleteMapping("/{policeNumber}")
    public ResponseEntity deleteCar(@PathVariable("policeNumber") String policeNumber) throws Exception {
        iCarServ.removeCar(policeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete car success", null));
    }

    @PutMapping("/{policeNumber}")
    public ResponseEntity editCar(@PathVariable("policeNumber") String policeNumber, @Valid @RequestBody CarReq carReq) throws Exception {
        Car mappedCar = modelMapper.map(carReq, Car.class);
        Car editedCar = iCarServ.editCar(mappedCar, policeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Edit car success", editedCar));
    }

    @GetMapping
    public ResponseEntity showAllCar() throws Exception {
        List<Car> carList = iCarServ.showAllCar();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Show all car success", carList));
    }
}
