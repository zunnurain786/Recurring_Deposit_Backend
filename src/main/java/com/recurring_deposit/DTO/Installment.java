package com.recurring_deposit.DTO;

import java.time.LocalDate;

public class Installment {

    private Integer installmentNumber;

    private Double installmentAmount;

    private LocalDate dueDate;

    private String status;

    public Installment() {
    }

    public Installment(
            Integer installmentNumber,
            Double installmentAmount,
            LocalDate dueDate,
            String status) {

        this.installmentNumber = installmentNumber;
        this.installmentAmount = installmentAmount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}