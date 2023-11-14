package com.demoApplication.firstDemoProject.controller;

import org.springframework.stereotype.Component;
/*
When spring loads @component it also uses custom Constructor so You need to define beans for it.
 */
@Component
public class Customer {
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
