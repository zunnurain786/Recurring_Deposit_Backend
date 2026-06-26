package com.recurring_deposit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.SchemeEntity;

@Repository
public interface SchemeRepository extends JpaRepository<SchemeEntity, Long> {

    List<SchemeEntity> findByActiveTrue();

    List<SchemeEntity> findBySchemeNameContainingIgnoreCase(String schemeName);

    boolean existsBySchemeNameIgnoreCase(String schemeName);

    long countByActiveTrue();
}