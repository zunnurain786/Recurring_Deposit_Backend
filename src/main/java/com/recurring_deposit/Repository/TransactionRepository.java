package com.recurring_deposit.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.TransactionEntity;

@Repository
public interface TransactionRepository
        extends JpaRepository<TransactionEntity, Long> {

    /*
     * Find all transactions of a specific RD Account
     */
    List<TransactionEntity> findByRdAccountRdAccountId(
            Long rdAccountId
    );

    /*
     * Find transaction using reference number
     */
    Optional<TransactionEntity> findByReferenceNumber(
            String referenceNumber
    );

    /*
     * Transactions between two dates
     * Used for:
     * - Today Collection
     * - Monthly Collection
     * - Yearly Collection
     */
    List<TransactionEntity> findByTransactionDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    /*
     * Recent transactions
     */
    List<TransactionEntity> findTop10ByOrderByTransactionDateDesc();

}