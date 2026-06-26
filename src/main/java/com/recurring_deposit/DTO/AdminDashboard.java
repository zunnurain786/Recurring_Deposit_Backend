package com.recurring_deposit.DTO;

import java.util.List;

public class AdminDashboard {

    // Admin Information
    private String adminName;
    private String adminCode;

    // User Statistics
    private Long totalUsers;
    private Long totalCustomers;
    private Long totalAgents;

    // Scheme Statistics
    private Long totalSchemes;

    // RD Account Statistics
    private Long activeRdAccounts;
    private Long closedRdAccounts;

    // Collection Statistics
    private Double todayCollection;
    private Double monthlyCollection;
    private Double yearlyCollection;

    // Profit Statistics
    private Double todayProfit;
    private Double totalProfit;

    // Customer Statistics
    private Long dueCustomersToday;
    private Long maturityCustomersToday;

    // Top Performing Agents
    private List<AgentPerformance> topAgents;

    // Maturity Customers
    private List<MaturityCustomer> maturityCustomers;

    // Recent Transactions
    private List<Transaction> recentTransactions;

    public AdminDashboard() {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Long getTotalAgents() {
        return totalAgents;
    }

    public void setTotalAgents(Long totalAgents) {
        this.totalAgents = totalAgents;
    }

    public Long getTotalSchemes() {
        return totalSchemes;
    }

    public void setTotalSchemes(Long totalSchemes) {
        this.totalSchemes = totalSchemes;
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

    public Double getYearlyCollection() {
        return yearlyCollection;
    }

    public void setYearlyCollection(Double yearlyCollection) {
        this.yearlyCollection = yearlyCollection;
    }

    public Double getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(Double todayProfit) {
        this.todayProfit = todayProfit;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Long getDueCustomersToday() {
        return dueCustomersToday;
    }

    public void setDueCustomersToday(Long dueCustomersToday) {
        this.dueCustomersToday = dueCustomersToday;
    }

    public Long getMaturityCustomersToday() {
        return maturityCustomersToday;
    }

    public void setMaturityCustomersToday(Long maturityCustomersToday) {
        this.maturityCustomersToday = maturityCustomersToday;
    }

    public List<AgentPerformance> getTopAgents() {
        return topAgents;
    }

    public void setTopAgents(List<AgentPerformance> topAgents) {
        this.topAgents = topAgents;
    }

    public List<MaturityCustomer> getMaturityCustomers() {
        return maturityCustomers;
    }

    public void setMaturityCustomers(List<MaturityCustomer> maturityCustomers) {
        this.maturityCustomers = maturityCustomers;
    }

    public List<Transaction> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<Transaction> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }
}