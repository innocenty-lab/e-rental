package org.putra.service;

import org.putra.entity.Transaction;

import java.util.List;

public interface ITransactionServ {
    Transaction order(Transaction transaction) throws Exception;
    List<Transaction> showAllTransaction() throws Exception;
}
