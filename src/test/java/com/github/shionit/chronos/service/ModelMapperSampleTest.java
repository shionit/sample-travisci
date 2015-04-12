package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by itoshion on 2015/04/10.
 */
public class ModelMapperSampleTest {

    public static final int MAX_COUNT = 1000000;
    private ModelMapperSample target = new ModelMapperSample();

    @Test
    public void testConvert() {
        Order order = createTestOrder();

        OrderDto result = target.convertNew(order);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }

    private static final Order createTestOrder() {
        final Name name = new Name();
        name.setFirstName("Taro");
        name.setLastName("Yamada");
        final Customer customer = new Customer();
        customer.setName(name);
        final Address address = new Address();
        address.setCity("Tokyo");
        address.setStreet("Central-Way");
        final Order order = new Order();
        order.setBillingAddress(address);
        order.setCustomer(customer);
        return order;
    }

    @Test
    public void testMapPerformance() {
        Order order = createTestOrder();
        OrderDto result;

        long start = System.nanoTime();

        for (int i = 0; i < MAX_COUNT; i++) {
            result = target.convertNew(order);
        }

        long end = System.nanoTime();
        System.out.println("testMapPerformance():" + String.valueOf(end - start));
    }
}
