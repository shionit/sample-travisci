package com.github.shionit.chronos.model;

import lombok.Data;

/**
 * Created by @shionit on 2015/04/10.
 */
@Data
public class OrderDto {
    private String customerFirstName;
    private String customerLastName;
    private String billingStreet;
    private String billingCity;
}
