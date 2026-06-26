package com.recurring_deposit.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recurring_deposit.Entity.AgentEntity;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    Optional<AgentEntity> findByAgentCode(String agentCode);

    Optional<AgentEntity> findByUserUserId(Long userId);

    List<AgentEntity> findByActiveTrue();

    boolean existsByAgentCode(String agentCode);

    boolean existsByUserUserId(Long userId);

    long countByActiveTrue();
}