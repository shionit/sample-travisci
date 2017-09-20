package com.github.shionit.chronos.model;

import lombok.Data;

/**
 * Created by @shionit on 2015/04/10.
 */
@Data
public class Order {
    private Customer customer;
    private Address billingAddress;
}
