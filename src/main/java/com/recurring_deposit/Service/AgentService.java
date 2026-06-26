package com.recurring_deposit.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recurring_deposit.DTO.AgentDashboard;
import com.recurring_deposit.Entity.AgentEntity;
import com.recurring_deposit.Entity.RdAccountStatus;
import com.recurring_deposit.Entity.TransactionEntity;
import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Repository.AgentRepository;
import com.recurring_deposit.Repository.CustomerRepository;
import com.recurring_deposit.Repository.RdAccountRepository;
import com.recurring_deposit.Repository.SchemeRepository;
import com.recurring_deposit.Repository.TransactionRepository;
import com.recurring_deposit.Repository.UserRepository;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RdAccountRepository rdAccountRepository;
    private final TransactionRepository transactionRepository;
    private final SchemeRepository schemeRepository;

    public AgentService(
            AgentRepository agentRepository,
            UserRepository userRepository,
            CustomerRepository customerRepository,
            RdAccountRepository rdAccountRepository,
            TransactionRepository transactionRepository,
            SchemeRepository schemeRepository) {

        this.agentRepository = agentRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.rdAccountRepository = rdAccountRepository;
        this.transactionRepository = transactionRepository;
        this.schemeRepository = schemeRepository;
    }

    /*
     * SAVE AGENT
     */
    public AgentEntity saveAgent(AgentEntity agent) {

        if (agent.getUser() == null ||
                agent.getUser().getUserId() == null) {

            throw new RuntimeException("User Id is required");
        }

        UserEntity user = userRepository
                .findById(agent.getUser().getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (agentRepository.existsByUserUserId(user.getUserId())) {

            throw new RuntimeException(
                    "Agent profile already exists");
        }

        agent.setUser(user);

        if (agent.getAgentCode() == null ||
                agent.getAgentCode().isBlank()) {

            String code;

            do {

                code =
                        "AGT-" +
                                UUID.randomUUID()
                                        .toString()
                                        .substring(0, 6)
                                        .toUpperCase();

            } while (
                    agentRepository.existsByAgentCode(code)
            );

            agent.setAgentCode(code);
        }

        if (agent.getJoiningDate() == null) {
            agent.setJoiningDate(LocalDate.now());
        }

        if (agent.getCommissionPercentage() == null) {
            agent.setCommissionPercentage(5.0);
        }

        if (agent.getActive() == null) {
            agent.setActive(true);
        }

        AgentEntity savedAgent =
                agentRepository.save(agent);

        user.setProfileCompleted(true);
        userRepository.save(user);

        return savedAgent;
    }

    /*
     * GET ALL AGENTS
     */
    public List<AgentEntity> getAllAgents() {
        return agentRepository.findAll();
    }

    /*
     * GET AGENT BY ID
     */
    public Optional<AgentEntity> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    /*
     * GET AGENT BY USER ID
     */
    public AgentEntity getAgentByUserId(Long userId) {

        return agentRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Agent not found"));
    }

    /*
     * UPDATE AGENT
     */
    public AgentEntity updateAgent(AgentEntity agent) {

        if (agent.getAgentId() == null) {
            throw new RuntimeException("Agent Id is required");
        }

        AgentEntity existingAgent =
                agentRepository.findById(agent.getAgentId())
                        .orElseThrow(() ->
                                new RuntimeException("Agent not found"));

        existingAgent.setBranchName(agent.getBranchName());
        existingAgent.setAddress(agent.getAddress());
        existingAgent.setCity(agent.getCity());
        existingAgent.setDateOfBirth(agent.getDateOfBirth());
        existingAgent.setCommissionPercentage(
                agent.getCommissionPercentage());
        existingAgent.setActive(agent.getActive());

        return agentRepository.save(existingAgent);
    }

    /*
     * DELETE AGENT
     */
    public void deleteAgent(Long id) {

        AgentEntity agent =
                agentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Agent not found"));

        agentRepository.delete(agent);
    }

    /*
     * AGENT DASHBOARD
     */
    public AgentDashboard getAgentDashboardByUserId(Long userId) {

        AgentEntity agent =
                agentRepository.findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException("Agent not found"));

        AgentDashboard dashboard =
                new AgentDashboard();

        /*
         * AGENT DETAILS
         */
        dashboard.setAgentName(
                agent.getUser().getFullName());

        dashboard.setAgentCode(
                agent.getAgentCode());

        dashboard.setBranchName(
                agent.getBranchName());

        dashboard.setJoiningDate(
                agent.getJoiningDate());

        dashboard.setActive(
                agent.getActive());

        dashboard.setCommissionPercentage(
                agent.getCommissionPercentage());

        /*
         * SCHEMES
         */
        dashboard.setTotalSchemes(
                schemeRepository.count());

        /*
         * RD ACCOUNTS OF THIS AGENT
         */
        List<?> myAccounts =
                rdAccountRepository
                        .findByAgentUserUserId(userId);

        long activeAccounts =
                rdAccountRepository
                        .findByAgentUserUserId(userId)
                        .stream()
                        .filter(a ->
                                a.getStatus() ==
                                        RdAccountStatus.ACTIVE)
                        .count();

        long closedAccounts =
                rdAccountRepository
                        .findByAgentUserUserId(userId)
                        .stream()
                        .filter(a ->
                                a.getStatus() ==
                                        RdAccountStatus.CLOSED)
                        .count();

        dashboard.setActiveRdAccounts(
                activeAccounts);

        dashboard.setClosedRdAccounts(
                closedAccounts);

        /*
         * COLLECTIONS
         */
        LocalDate today = LocalDate.now();

        LocalDateTime startOfDay =
                today.atStartOfDay();

        LocalDateTime endOfDay =
                today.atTime(23, 59, 59);

        double todayCollection =
                transactionRepository
                        .findByTransactionDateBetween(
                                startOfDay,
                                endOfDay)
                        .stream()
                        .mapToDouble(
                                TransactionEntity::getAmount)
                        .sum();

        dashboard.setTodayCollection(
                todayCollection);

        /*
         * MONTHLY COLLECTION
         */
        LocalDate firstDayOfMonth =
                today.withDayOfMonth(1);

        double monthlyCollection =
                transactionRepository.findAll()
                        .stream()
                        .filter(tx ->
                                !tx.getTransactionDate()
                                        .toLocalDate()
                                        .isBefore(firstDayOfMonth))
                        .mapToDouble(
                                TransactionEntity::getAmount)
                        .sum();

        /*
         * PROFITS
         */
        double commission =
                agent.getCommissionPercentage();

        dashboard.setTodayProfit(
                todayCollection *
                        commission / 100);

        dashboard.setMonthlyProfit(
                monthlyCollection *
                        commission / 100);

        double totalCollection =
                transactionRepository.findAll()
                        .stream()
                        .mapToDouble(
                                TransactionEntity::getAmount)
                        .sum();

        dashboard.setTotalProfit(
                totalCollection *
                        commission / 100);

        /*
         * PENDING COLLECTION
         */
        double pendingCollection =
                rdAccountRepository
                        .findByAgentUserUserId(userId)
                        .stream()
                        .filter(rd ->
                                rd.getStatus() ==
                                        RdAccountStatus.ACTIVE)
                        .mapToDouble(
                                a -> a.getMonthlyDepositAmount())
                        .sum();

        dashboard.setPendingCollection(
                pendingCollection);

        /*
         * CUSTOMER COUNTS
         * Replace with actual agent-customer mapping later
         */
        dashboard.setTotalCustomers(
                customerRepository.count());

        dashboard.setPaidCustomersToday(0L);
        dashboard.setRemainingCustomersToday(0L);

        return dashboard;
    }
}
