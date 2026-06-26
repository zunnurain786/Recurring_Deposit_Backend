package com.recurring_deposit.DTO;

import java.time.LocalDate;

public class RdAccountResponse {

    private Long rdAccountId;

    private String accountNumber;

    private String customerName;

    private String schemeName;

    private Double monthlyDepositAmount;

    private LocalDate startDate;

    private LocalDate maturityDate;

    private Double maturityAmount;

    private Integer paidEmi;

    private Integer pendingEmi;

    private Integer emiDay;

    private String status;

    private String agentName;

    // Default Constructor
    public RdAccountResponse() {
    }

    // Getters and Setters

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

    public Integer getEmiDay() {
        return emiDay;
    }

    public void setEmiDay(Integer emiDay) {
        this.emiDay = emiDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}