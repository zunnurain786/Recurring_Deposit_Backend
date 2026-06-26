package com.recurring_deposit.DTO;

import java.time.LocalDate;

public class AgentRdAccount {

    private Long rdAccountId;

    private String accountNumber;

    private String customerName;

    private String schemeName;

    private Double monthlyDepositAmount;

    private LocalDate startDate;

    private LocalDate maturityDate;

    private String status;

    private Double totalDepositedAmount;

    private Double maturityAmount;

    public AgentRdAccount() {
    }

    public AgentRdAccount(
            Long rdAccountId,
            String accountNumber,
            String customerName,
            String schemeName,
            Double monthlyDepositAmount,
            LocalDate startDate,
            LocalDate maturityDate,
            String status,
            Double totalDepositedAmount,
            Double maturityAmount) {

        this.rdAccountId = rdAccountId;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.schemeName = schemeName;
        this.monthlyDepositAmount = monthlyDepositAmount;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.status = status;
        this.totalDepositedAmount = totalDepositedAmount;
        this.maturityAmount = maturityAmount;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalDepositedAmount() {
        return totalDepositedAmount;
    }

    public void setTotalDepositedAmount(Double totalDepositedAmount) {
        this.totalDepositedAmount = totalDepositedAmount;
    }

    public Double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }
}