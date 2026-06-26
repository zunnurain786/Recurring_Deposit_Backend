package com.recurring_deposit.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.RdAccountEntity;
import com.recurring_deposit.Entity.RdAccountStatus;

@Repository
public interface RdAccountRepository
        extends JpaRepository<RdAccountEntity, Long> {

    Optional<RdAccountEntity> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    List<RdAccountEntity> findByCustomerUserUserId(Long userId);

    List<RdAccountEntity> findByAgentUserUserId(Long userId);

    List<RdAccountEntity> findByStatus(RdAccountStatus status);

    long countByStatus(RdAccountStatus status);

    long countByAgentUserUserId(Long userId);

    long countByAgentUserUserIdAndStatus(
            Long userId,
            RdAccountStatus status
    );
}