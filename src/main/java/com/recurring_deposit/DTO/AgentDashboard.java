package com.recurring_deposit.DTO;

import java.time.LocalDate;
import java.util.List;

public class AgentDashboard {

    // =========================
    // Agent Information
    // =========================
    private String agentName;
    private String agentCode;
    private String branchName;

    private LocalDate joiningDate;
    private Boolean active;

    // =========================
    // Collection Summary
    // =========================
    private Double todayCollection;
    private Double monthlyCollection;
    private Double totalCollection;
    private Double pendingCollection;

    private Long paidCustomersToday;
    private Long remainingCustomersToday;

    // =========================
    // Customer Statistics
    // =========================
    private Long totalCustomers;

    // =========================
    // RD Account Statistics
    // =========================
    private Long totalRdAccounts;
    private Long activeRdAccounts;
    private Long closedRdAccounts;

    // =========================
    // Scheme Statistics
    // =========================
    private Long totalSchemes;

    // =========================
    // Earnings
    // =========================
    private Double commissionPercentage;
    private Double todayProfit;
    private Double monthlyProfit;
    private Double totalProfit;

    // =========================
    // Upcoming Maturity Customers
    // =========================
    private List<MaturityCustomer> maturityCustomers;

    // =========================
    // Agent RD Accounts
    // =========================
    private List<AgentRdAccount> rdAccounts;

    public AgentDashboard() {
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getTodayCollection() {
        return todayCollection;
    }

    public void setTodayCollection(Double todayCollection) {
        this.todayCollection = todayCollection;
    }

    public Double getMonthlyCollection() {
        return monthlyCollection;
    }

    public void setMonthlyCollection(Double monthlyCollection) {
        this.monthlyCollection = monthlyCollection;
    }

    public Double getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(Double totalCollection) {
        this.totalCollection = totalCollection;
    }

    public Double getPendingCollection() {
        return pendingCollection;
    }

    public void setPendingCollection(Double pendingCollection) {
        this.pendingCollection = pendingCollection;
    }

    public Long getPaidCustomersToday() {
        return paidCustomersToday;
    }

    public void setPaidCustomersToday(Long paidCustomersToday) {
        this.paidCustomersToday = paidCustomersToday;
    }

    public Long getRemainingCustomersToday() {
        return remainingCustomersToday;
    }

    public void setRemainingCustomersToday(Long remainingCustomersToday) {
        this.remainingCustomersToday = remainingCustomersToday;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Long getTotalRdAccounts() {
        return totalRdAccounts;
    }

    public void setTotalRdAccounts(Long totalRdAccounts) {
        this.totalRdAccounts = totalRdAccounts;
    }

    public Long getActiveRdAccounts() {
        return activeRdAccounts;
    }

    public void setActiveRdAccounts(Long activeRdAccounts) {
        this.activeRdAccounts = activeRdAccounts;
    }

    public Long getClosedRdAccounts() {
        return closedRdAccounts;
    }

    public void setClosedRdAccounts(Long closedRdAccounts) {
        this.closedRdAccounts = closedRdAccounts;
    }

    public Long getTotalSchemes() {
        return totalSchemes;
    }

    public void setTotalSchemes(Long totalSchemes) {
        this.totalSchemes = totalSchemes;
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public Double getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(Double todayProfit) {
        this.todayProfit = todayProfit;
    }

    public Double getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(Double monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public List<MaturityCustomer> getMaturityCustomers() {
        return maturityCustomers;
    }

    public void setMaturityCustomers(List<MaturityCustomer> maturityCustomers) {
        this.maturityCustomers = maturityCustomers;
    }

    public List<AgentRdAccount> getRdAccounts() {
        return rdAccounts;
    }

    public void setRdAccounts(List<AgentRdAccount> rdAccounts) {
        this.rdAccounts = rdAccounts;
    }
}