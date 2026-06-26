package com.recurring_deposit.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(
    name = "transactions",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_transaction_reference",
            columnNames = "reference_number"
        )
    }
)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    @Column(nullable = false)
    private Double amount;

    @NotNull(message = "Transaction type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType transactionType;

    @NotNull(message = "Payment mode is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMode paymentMode;

    @NotBlank(message = "Reference number is required")
    @Column(
        name = "reference_number",
        nullable = false,
        unique = true,
        length = 100
    )
    private String referenceNumber;

    @NotNull(message = "Transaction date is required")
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "rd_account_id",
        nullable = false
    )
    private RdAccountEntity rdAccount;

    /*
     * AUTO SET TRANSACTION DATE
     */
    @PrePersist
    public void prePersist() {

        if (transactionDate == null) {

            transactionDate = LocalDateTime.now();
        }
    }

    // ==========================
    // GETTERS AND SETTERS
    // ==========================

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public RdAccountEntity getRdAccount() {
        return rdAccount;
    }

    public void setRdAccount(RdAccountEntity rdAccount) {
        this.rdAccount = rdAccount;
    }
}