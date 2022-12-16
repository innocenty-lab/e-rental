package org.putra.service;

import org.putra.entity.Customer;
import org.putra.entity.Price;
import org.putra.entity.Transaction;
import org.putra.entity.TransactionDetail;
import org.putra.repository.ICustomerRepo;
import org.putra.repository.IPriceRepo;
import org.putra.repository.ITransactionDetail;
import org.putra.repository.ITransactionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServImpl implements ITransactionServ {
    private final ITransactionRepo iTransactionRepo;
    private final ICustomerRepo iCustomerRepo;
    private final IPriceRepo iPriceRepo;
    private final ITransactionDetail iTransactionDetail;

    public TransactionServImpl(ITransactionRepo iTransactionRepo, ICustomerRepo iCustomerRepo, IPriceRepo iPriceRepo, ITransactionDetail iTransactionDetail) {
        this.iTransactionRepo = iTransactionRepo;
        this.iCustomerRepo = iCustomerRepo;
        this.iPriceRepo = iPriceRepo;
        this.iTransactionDetail = iTransactionDetail;
    }

    @Transactional
    @Override
    public Transaction order(Transaction transaction) throws Exception {
        try {
            // save transaction
            Customer existingCustomer = iCustomerRepo.findByCustomerNik("1234");
            Transaction newTransaction = new Transaction();
            newTransaction.setCustomer(existingCustomer);
            newTransaction.setBookedDate(transaction.getBookedDate());
            newTransaction.setIsPaid(transaction.getIsPaid());
            newTransaction.setPaidDate(transaction.getPaidDate());

            Transaction savedNewTransaction = iTransactionRepo.save(newTransaction);

            // iterable save transaction detail
            List<TransactionDetail> transactionDetailListOrder = new ArrayList<>();
            transaction.getTransactionDetails().stream().forEach(transactionDetail -> {
                transactionDetailListOrder.add(transactionDetail);
            });

            List<Price> existingPriceList = iPriceRepo.findAllPrice(); // n (obj Price)
            List<String> priceIdListOrder = new ArrayList<>(); // 2 (string)

            for (Price epl:existingPriceList) {
                for (String plo:priceIdListOrder) {
                    if (epl.getPriceId().equals(plo)) {
                        transactionDetailListOrder.stream().forEach(transactionDetail -> {
                            transactionDetail.setPrice(epl);
                        });
                    }
                }
            }

//            Transaction existingTransaction = iTransactionRepo.findByCustomerAndBookedDate(existingCustomer, transaction.getBookedDate());

            transactionDetailListOrder.stream().forEach((transactionDetail) -> {
                transactionDetailListOrder.stream().forEach(transactionDetail1 -> {
                    transactionDetail.setTransaction(savedNewTransaction);
                });
            });

            iTransactionDetail.saveAll(transactionDetailListOrder);

            Transaction existingTransactionFix = iTransactionRepo.findByCustomerAndBookedDate(existingCustomer, transaction.getBookedDate());
            System.out.println(existingTransactionFix);

            return existingTransactionFix;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Transaction> showAllTransaction() throws Exception {
        List<Transaction> transactionList = iTransactionRepo.findAllTransaction();
        return transactionList;
    }
}