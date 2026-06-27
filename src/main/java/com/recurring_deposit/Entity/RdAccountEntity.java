package com.recurring_deposit.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(
        name = "rd_accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "account_number")
        }
)
public class RdAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rdAccountId;

    @NotBlank(message = "Account number is required")
    @Column(
            name = "account_number",
            nullable = false,
            unique = true,
            length = 30
    )
    private String accountNumber;

    @NotNull(message = "Monthly deposit amount is required")
    @Positive(message = "Monthly deposit amount must be greater than 0")
    @Column(nullable = false)
    private Double monthlyDepositAmount;

    @NotNull(message = "Start date is required")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Maturity date is required")
    @Column(nullable = false)
    private LocalDate maturityDate;

    @NotNull(message = "Maturity amount is required")
    @Positive(message = "Maturity amount must be greater than 0")
    @Column(nullable = false)
    private Double maturityAmount;

    @Column(nullable = false)
    private Integer paidEmi = 0;

    @Column(nullable = false)
    private Integer pendingEmi = 0;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RdAccountStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "scheme_id",
            nullable = false
    )
    private SchemeEntity scheme;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    public RdAccountEntity() {
    }

    public Long getRdAccountId() {
        return rdAccountId;
    }

    public void setRdAccountId(Long rdAccountId) {
        this.rdAccountId = rdAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getMonthlyDepositAmount() {
        return monthlyDepositAmount;
    }

    public void setMonthlyDepositAmount(Double monthlyDepositAmount) {
        this.monthlyDepositAmount = monthlyDepositAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Integer getPaidEmi() {
        return paidEmi;
    }

    public void setPaidEmi(Integer paidEmi) {
        this.paidEmi = paidEmi;
    }

    public Integer getPendingEmi() {
        return pendingEmi;
    }

    public void setPendingEmi(Integer pendingEmi) {
        this.pendingEmi = pendingEmi;
    }

    public RdAccountStatus getStatus() {
        return status;
    }

    public void setStatus(RdAccountStatus status) {
        this.status = status;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public SchemeEntity getScheme() {
        return scheme;
    }

    public void setScheme(SchemeEntity scheme) {
        this.scheme = scheme;
    }

    public AgentEntity getAgent() {
        return agent;
    }

    public void setAgent(AgentEntity agent) {
        this.agent = agent;
    }
}
