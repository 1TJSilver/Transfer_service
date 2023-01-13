package com.example.hw_7_4_1_1_transfer_service.base;

public class Operation {
    private Account from;
    private Account to;
    private Integer amount;
    private String operationID;

    public Operation(Account from, Account to, Integer amount, String operationID){
        this.from = from;
        this.to = to;
        this.operationID = operationID;
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getOperationID() {
        return operationID;
    }
}
