package com.recurring_deposit.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.InstallmentEntity;
import com.recurring_deposit.Entity.InstallmentStatus;

@Repository
public interface InstallmentRepository
        extends JpaRepository<InstallmentEntity, Long> {

    /*
     * All installments of a specific RD Account
     */
    List<InstallmentEntity> findByRdAccountRdAccountId(
            Long rdAccountId
    );

    /*
     * Find installments by status
     * PAID / PENDING
     */
    List<InstallmentEntity> findByStatus(
            InstallmentStatus status
    );

    /*
     * Total installments of an RD Account
     */
    long countByRdAccountRdAccountId(
            Long rdAccountId
    );

    /*
     * Count PAID or PENDING installments
     */
    long countByRdAccountRdAccountIdAndStatus(
            Long rdAccountId,
            InstallmentStatus status
    );

    /*
     * Upcoming due installments
     */
    List<InstallmentEntity> findByPaymentDate(
            LocalDate paymentDate
    );

    /*
     * Upcoming due installments by status
     */
    List<InstallmentEntity> findByPaymentDateAndStatus(
            LocalDate paymentDate,
            InstallmentStatus status
    );

}