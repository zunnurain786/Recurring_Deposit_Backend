package com.recurring_deposit.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.recurring_deposit.DTO.RdAccountRequest;
import com.recurring_deposit.DTO.RdAccountResponse;
import com.recurring_deposit.Entity.AgentEntity;
import com.recurring_deposit.Entity.CustomerEntity;
import com.recurring_deposit.Entity.RdAccountEntity;
import com.recurring_deposit.Entity.RdAccountStatus;
import com.recurring_deposit.Entity.SchemeEntity;
import com.recurring_deposit.Repository.AgentRepository;
import com.recurring_deposit.Repository.CustomerRepository;
import com.recurring_deposit.Repository.RdAccountRepository;
import com.recurring_deposit.Repository.SchemeRepository;
import com.recurring_deposit.Repository.UserRepository;

@Service
public class RdAccountService {

    private final RdAccountRepository rdAccountRepository;
    private final SchemeRepository schemeRepository;
    private final AgentRepository agentRepository;
    private final CustomerRepository customerRepository;

    public RdAccountService(
            RdAccountRepository rdAccountRepository,
            UserRepository userRepository,
            SchemeRepository schemeRepository,
            AgentRepository agentRepository,
            CustomerRepository customerRepository) {

        this.rdAccountRepository = rdAccountRepository;
        this.schemeRepository = schemeRepository;
        this.agentRepository = agentRepository;
        this.customerRepository = customerRepository;
    }

    /*
     * ENTITY -> DTO
     */
    private RdAccountResponse convertToResponse(
            RdAccountEntity rdAccount) {

        RdAccountResponse response =
                new RdAccountResponse();

        response.setRdAccountId(
                rdAccount.getRdAccountId());

        response.setAccountNumber(
                rdAccount.getAccountNumber());

        response.setCustomerName(
                rdAccount.getCustomer()
                        .getUser()
                        .getFullName());

        response.setSchemeName(
                rdAccount.getScheme()
                        .getSchemeName());

        response.setMonthlyDepositAmount(
                rdAccount.getMonthlyDepositAmount());

        response.setStartDate(
                rdAccount.getStartDate());

        response.setMaturityDate(
                rdAccount.getMaturityDate());

        response.setMaturityAmount(
                rdAccount.getMaturityAmount());

        response.setPaidEmi(
                rdAccount.getPaidEmi());

        response.setPendingEmi(
                rdAccount.getPendingEmi());

        response.setEmiDay(
                rdAccount.getStartDate()
                        .getDayOfMonth());

        response.setStatus(
                rdAccount.getStatus().name());

        response.setAgentName(
                rdAccount.getAgent()
                        .getUser()
                        .getFullName());

        return response;
    }

    /*
     * CREATE RD ACCOUNT
     */
    public RdAccountResponse createRdAccount(
            RdAccountRequest request) {

        CustomerEntity customer =
                customerRepository.findById(
                        request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found"));

        SchemeEntity scheme =
                schemeRepository.findById(
                        request.getSchemeId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Scheme not found"));

        Double monthlyAmount =
                request.getMonthlyDepositAmount();

        if (monthlyAmount < scheme.getMinimumAmount().doubleValue()
                || monthlyAmount > scheme.getMaximumAmount().doubleValue()) {

            throw new RuntimeException(
                    "Deposit amount must be between "
                            + scheme.getMinimumAmount()
                            + " and "
                            + scheme.getMaximumAmount());
        }

        List<AgentEntity> agents =
                agentRepository.findAll();

        if (agents.isEmpty()) {

            throw new RuntimeException(
                    "No agents available");
        }

        AgentEntity randomAgent =
                agents.get(
                        ThreadLocalRandom.current()
                                .nextInt(agents.size()));

        String accountNumber;

        do {

            accountNumber =
                    "RD-"
                            + LocalDate.now().getYear()
                            + "-"
                            + (100000 +
                            ThreadLocalRandom.current()
                                    .nextInt(900000));

        } while (
                rdAccountRepository
                        .existsByAccountNumber(
                                accountNumber));

        Integer months =
                scheme.getDurationMonths();

        LocalDate maturityDate =
                request.getStartDate()
                        .plusMonths(months);

        Double totalDeposit =
                monthlyAmount * months;

        Double maturityAmount =
                totalDeposit +
                        ((totalDeposit *
                                scheme.getInterestRate() *
                                months)
                                / (12 * 100));

        RdAccountEntity rdAccount =
                new RdAccountEntity();

        rdAccount.setAccountNumber(
                accountNumber);

        rdAccount.setMonthlyDepositAmount(
                monthlyAmount);

        rdAccount.setStartDate(
                request.getStartDate());

        rdAccount.setMaturityDate(
                maturityDate);

        rdAccount.setMaturityAmount(
                Math.round(
                        maturityAmount * 100.0)
                        / 100.0);

        rdAccount.setStatus(
                RdAccountStatus.ACTIVE);

        rdAccount.setCustomer(
                customer);

        rdAccount.setScheme(
                scheme);

        rdAccount.setAgent(
                randomAgent);

        rdAccount.setPaidEmi(0);

        rdAccount.setPendingEmi(
                months);

        RdAccountEntity saved =
                rdAccountRepository.save(
                        rdAccount);

        return convertToResponse(
                saved);
    }

    /*
     * CUSTOMER RD ACCOUNTS
     */
    public List<RdAccountResponse>
    getRdAccountByCustomerId(
            Long userId) {

        return rdAccountRepository
                .findByCustomerUserUserId(
                        userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    /*
     * AGENT RD ACCOUNTS
     */
    public List<RdAccountResponse>
    getAgentAccounts(
            Long userId) {

        return rdAccountRepository
                .findByAgentUserUserId(
                        userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    public RdAccountResponse getRdAccountById(Long id) {

        RdAccountEntity rdAccount =
                rdAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "RD Account not found"));

        return convertToResponse(rdAccount);
    }

    public List<RdAccountResponse> getAllRdAccounts() {

        return rdAccountRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    public RdAccountResponse updateRdAccount(
            Long id,
            RdAccountRequest request) {

        RdAccountEntity rdAccount =
                rdAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "RD Account not found"));

        SchemeEntity scheme =
                schemeRepository.findById(
                        request.getSchemeId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Scheme not found"));

        CustomerEntity customer =
                customerRepository.findById(
                        request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found"));

        Double monthlyAmount =
                request.getMonthlyDepositAmount();

        if (monthlyAmount < scheme.getMinimumAmount().doubleValue()
                || monthlyAmount > scheme.getMaximumAmount().doubleValue()) {

            throw new RuntimeException(
                    "Deposit amount must be between "
                            + scheme.getMinimumAmount()
                            + " and "
                            + scheme.getMaximumAmount());
        }

        Integer months =
                scheme.getDurationMonths();

        LocalDate maturityDate =
                request.getStartDate()
                        .plusMonths(months);

        Double totalDeposit =
                monthlyAmount * months;

        Double maturityAmount =
                totalDeposit +
                        ((totalDeposit
                                * scheme.getInterestRate()
                                * months)
                                / (12 * 100));

        rdAccount.setMonthlyDepositAmount(
                monthlyAmount);

        rdAccount.setStartDate(
                request.getStartDate());

        rdAccount.setMaturityDate(
                maturityDate);

        rdAccount.setMaturityAmount(
                Math.round(maturityAmount * 100.0) / 100.0);

        rdAccount.setCustomer(customer);

        rdAccount.setScheme(scheme);

        RdAccountEntity updatedAccount =
                rdAccountRepository.save(rdAccount);

        return convertToResponse(updatedAccount);
    }
    
    public void deleteRdAccount(Long id) {

        RdAccountEntity rdAccount =
                rdAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "RD Account not found"));

        rdAccountRepository.delete(rdAccount);
    }
    
}