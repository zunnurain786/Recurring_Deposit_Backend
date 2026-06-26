package com.recurring_deposit.DTO;

import java.time.LocalDate;

public class MaturityCustomer {

    private Long customerId;

    private String customerName;

    private String accountNumber;

    private String mobileNumber;

    private String schemeName;

    private Double monthlyDepositAmount;

    private Double maturityAmount;

    private LocalDate maturityDate;

    private String rdAccountStatus;

    public MaturityCustomer() {
    }

    public MaturityCustomer(
            Long customerId,
            String customerName,
            String accountNumber,
            String mobileNumber,
            String schemeName,
            Double monthlyDepositAmount,
            Double maturityAmount,
            LocalDate maturityDate,
            String rdAccountStatus) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
        this.schemeName = schemeName;
        this.monthlyDepositAmount = monthlyDepositAmount;
        this.maturityAmount = maturityAmount;
        this.maturityDate = maturityDate;
        this.rdAccountStatus = rdAccountStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public Double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getRdAccountStatus() {
        return rdAccountStatus;
    }

    public void setRdAccountStatus(String rdAccountStatus) {
        this.rdAccountStatus = rdAccountStatus;
    }

    @Override
    public String toString() {
        return "MaturityCustomer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", maturityAmount=" + maturityAmount +
                ", maturityDate=" + maturityDate +
                '}';
    }
}