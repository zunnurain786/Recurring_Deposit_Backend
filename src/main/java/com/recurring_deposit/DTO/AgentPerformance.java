package com.recurring_deposit.DTO;

public class AgentPerformance {

    private String agentName;

    private String agentCode;

    private String branchName;

    private Long totalCustomers;

    private Double todayCollection;

    private Double totalCollection;

    private Double todayProfit;

    private Double monthlyProfit;

    public AgentPerformance() {
    }

    public AgentPerformance(
            String agentName,
            String agentCode,
            String branchName,
            Long totalCustomers,
            Double todayCollection,
            Double totalCollection,
            Double todayProfit,
            Double monthlyProfit) {

        this.agentName = agentName;
        this.agentCode = agentCode;
        this.branchName = branchName;
        this.totalCustomers = totalCustomers;
        this.todayCollection = todayCollection;
        this.totalCollection = totalCollection;
        this.todayProfit = todayProfit;
        this.monthlyProfit = monthlyProfit;
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

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Double getTodayCollection() {
        return todayCollection;
    }

    public void setTodayCollection(Double todayCollection) {
        this.todayCollection = todayCollection;
    }

    public Double getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(Double totalCollection) {
        this.totalCollection = totalCollection;
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
}