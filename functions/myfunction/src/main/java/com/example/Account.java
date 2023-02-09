package com.example;

public class Account {
    private final String id;
    private final String name;
    private final String accountNumber;

    
    public Account(String id, String name,String accountNumber) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

}
