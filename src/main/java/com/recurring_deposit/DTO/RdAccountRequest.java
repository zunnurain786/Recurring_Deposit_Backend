package com.recurring_deposit.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RdAccountRequest {

    @NotNull(message = "Monthly deposit amount is required")
    @Positive(message = "Monthly deposit amount must be greater than 0")
    private Double monthlyDepositAmount;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotNull(message = "Scheme Id is required")
    private Long schemeId;

    public RdAccountRequest() {
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }
}