package com.recurring_deposit.DTO;

import java.time.LocalDate;
import java.util.List;

public class CustomerInstallment {

    private Long rdAccountId;

    private String accountNumber;

    private String customerName;

    private String schemeName;

    private Double monthlyDepositAmount;

    private Integer durationMonths;

    private LocalDate startDate;

    private LocalDate maturityDate;

    private Double maturityAmount;

    private String accountStatus;

    private Integer paidInstallments;

    private Integer pendingInstallments;

    private List<Installment> installments;

    public CustomerInstallment() {
    }

    public CustomerInstallment(
            Long rdAccountId,
            String accountNumber,
            String customerName,
            String schemeName,
            Double monthlyDepositAmount,
            Integer durationMonths,
            LocalDate startDate,
            LocalDate maturityDate,
            Double maturityAmount,
            String accountStatus,
            Integer paidInstallments,
            Integer pendingInstallments,
            List<Installment> installments) {

        this.rdAccountId = rdAccountId;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.schemeName = schemeName;
        this.monthlyDepositAmount = monthlyDepositAmount;
        this.durationMonths = durationMonths;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.maturityAmount = maturityAmount;
        this.accountStatus = accountStatus;
        this.paidInstallments = paidInstallments;
        this.pendingInstallments = pendingInstallments;
        this.installments = installments;
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

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getPaidInstallments() {
        return paidInstallments;
    }

    public void setPaidInstallments(Integer paidInstallments) {
        this.paidInstallments = paidInstallments;
    }

    public Integer getPendingInstallments() {
        return pendingInstallments;
    }

    public void setPendingInstallments(Integer pendingInstallments) {
        this.pendingInstallments = pendingInstallments;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}