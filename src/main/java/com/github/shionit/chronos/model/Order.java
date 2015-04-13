package com.github.shionit.chronos.model;

/**
 * Created by @shionit on 2015/04/10.
 */
public class Order {
    private Customer customer;
    private Address billingAddress;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
