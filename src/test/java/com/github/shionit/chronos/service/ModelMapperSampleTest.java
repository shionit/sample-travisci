package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by itoshion on 2015/04/10.
 */
public class ModelMapperSampleTest {

    private ModelMapperSample target = new ModelMapperSample();

    @Test
    public void testConvert() {
        Name name = new Name();
        name.setFirstName("Taro");
        name.setLastName("Yamada");
        Customer customer = new Customer();
        customer.setName(name);
        Address address = new Address();
        address.setCity("Tokyo");
        address.setStreet("Central-Way");
        Order order = new Order();
        order.setBillingAddress(address);
        order.setCustomer(customer);

        OrderDto result = target.convert(order);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }
}
