package org.putra.controller;

import org.modelmapper.ModelMapper;
import org.putra.constant.UrlMapping;
import org.putra.entity.Price;
import org.putra.entity.request.PriceReq;
import org.putra.entity.response.SuccessResponse;
import org.putra.service.IPriceServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.PRICE_URL)
@Validated
public class PriceController {
    private final IPriceServ iPriceServ;
    private final ModelMapper modelMapper;

    public PriceController(IPriceServ iPriceServ, ModelMapper modelMapper) {
        this.iPriceServ = iPriceServ;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity insertPrice(@Valid @RequestBody PriceReq priceReq) throws Exception {
        Price mappedPrice = modelMapper.map(priceReq, Price.class);
        Price insertedPrice = iPriceServ.insertPrice(mappedPrice);
        System.out.println(insertedPrice);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Insert car success", insertedPrice));
    }

    @PutMapping("/{priceId}")
    public ResponseEntity editPrice(@PathVariable("priceId") String priceId, @Valid @RequestBody PriceReq priceReq) throws Exception {
        Price mappedPrice = modelMapper.map(priceReq, Price.class);
        Price editedPrice = iPriceServ.editPrice(mappedPrice, priceId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Edit price success", editedPrice));
    }

    @DeleteMapping("/{priceId}")
    public ResponseEntity deletePrice(@PathVariable("priceId") String priceId) throws Exception {
        iPriceServ.removePrice(priceId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete price success", null));
    }

    @GetMapping
    public ResponseEntity showAllPrice() throws Exception {
        List<Price> priceList = iPriceServ.showAllPrice();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Show all price success", priceList));
    }
}
