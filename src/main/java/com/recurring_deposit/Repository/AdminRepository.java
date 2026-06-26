package com.recurring_deposit.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.AdminEntity;

@Repository
public interface AdminRepository
        extends JpaRepository<AdminEntity, Long> {

	AdminEntity findByUserUserId(Long userId);

    boolean existsByUserUserId(Long userId);

	boolean existsByAdminCode(String adminCode);

}