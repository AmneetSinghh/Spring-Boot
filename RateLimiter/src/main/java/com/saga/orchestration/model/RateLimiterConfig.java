package com.saga.orchestration.model;

import com.saga.orchestration.util.Identifier;

public class RateLimiterConfig {
    private int operations; // max request limit in interval.
    private String operation; // api request id
    private Identifier identifier; // userId / session Id
    private int seconds; // interval in seconds.

    public int getOperations() {
        return operations;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public RateLimiterConfig(int operations, String operation, Identifier identifier, int seconds) {
        this.operations = operations;
        this.operation = operation;
        this.identifier = identifier;
        this.seconds = seconds;
    }
}

