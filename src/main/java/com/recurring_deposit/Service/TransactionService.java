package com.recurring_deposit.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recurring_deposit.Entity.TransactionEntity;
import com.recurring_deposit.Repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(
            TransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
    }

    /*
     * SAVE TRANSACTION
     */
    public TransactionEntity saveTransaction(
            TransactionEntity transaction) {

        if (transaction.getRdAccount() == null) {
            throw new RuntimeException(
                    "RD Account is required");
        }

        if (transaction.getAmount() == null ||
                transaction.getAmount() <= 0) {

            throw new RuntimeException(
                    "Transaction amount must be greater than zero");
        }

        return transactionRepository.save(
                transaction);
    }

    /*
     * GET ALL TRANSACTIONS
     */
    public List<TransactionEntity> getAllTransactions() {

        return transactionRepository.findAll();
    }

    /*
     * GET TRANSACTION BY ID
     */
    public TransactionEntity getTransactionById(
            Long id) {

        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Transaction not found"));
    }

    /*
     * UPDATE TRANSACTION
     */
    public TransactionEntity updateTransaction(
            TransactionEntity transaction) {

        if (transaction.getTransactionId() == null) {

            throw new RuntimeException(
                    "Transaction Id is required");
        }

        if (!transactionRepository.existsById(
                transaction.getTransactionId())) {

            throw new RuntimeException(
                    "Transaction not found");
        }

        return transactionRepository.save(
                transaction);
    }

    /*
     * DELETE TRANSACTION
     */
    public void deleteTransaction(
            Long id) {

        if (!transactionRepository.existsById(
                id)) {

            throw new RuntimeException(
                    "Transaction not found");
        }

        transactionRepository.deleteById(
                id);
    }
}