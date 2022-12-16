package org.putra.controller;

import org.modelmapper.ModelMapper;
import org.putra.constant.UrlMapping;
import org.putra.entity.Transaction;
import org.putra.entity.request.TransactionReq;
import org.putra.entity.response.SuccessResponse;
import org.putra.service.ITransactionServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.TRANSACTION_URL)
@Validated
public class TransactionController {
    private final ITransactionServ iTransactionServ;
    private final ModelMapper modelMapper;

    public TransactionController(ITransactionServ iTransactionServ, ModelMapper modelMapper) {
        this.iTransactionServ = iTransactionServ;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity orderCar(@Valid @RequestBody TransactionReq transactionReq) throws Exception {
        Transaction mappedTransaction = modelMapper.map(transactionReq, Transaction.class);
        Transaction newTransaction = iTransactionServ.order(mappedTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Order car success", newTransaction));
    }

    @GetMapping
    public ResponseEntity showAllTransaction() throws Exception {
        List<Transaction> existingTransactionList = iTransactionServ.showAllTransaction();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Show all transaction success", existingTransactionList));
    }
}
