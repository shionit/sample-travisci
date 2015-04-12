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

        // warm up
        for (int i = 0; i < MAX_COUNT; i++) {
            result = target.convertNew(order);
        }
        result = new OrderDto();
        for (int i = 0; i < MAX_COUNT; i++) {
            target.convert(order, result);
        }

        // measure process time.
        long start = System.currentTimeMillis();

        // create Instance and Convert.
        for (int i = 0; i < MAX_COUNT; i++) {
            result = target.convertNew(order);
        }

        long end = System.currentTimeMillis();
        System.out.println("testConvertNew():" + String.valueOf(end - start));

        start = System.currentTimeMillis();

        // convert to prepared Instance.
        for (int i = 0; i < MAX_COUNT; i++) {
            target.convert(order, result);
        }

        end = System.currentTimeMillis();
        System.out.println("testConvert()   :" + String.valueOf(end - start));

        target.prepareMapper();

        start = System.currentTimeMillis();

        // create Instance and Convert.
        for (int i = 0; i < MAX_COUNT; i++) {
            result = target.convertNew(order);
        }

        end = System.currentTimeMillis();
        System.out.println("testConvertNew():" + String.valueOf(end - start));

        start = System.currentTimeMillis();

        // convert to prepared Instance.
        for (int i = 0; i < MAX_COUNT; i++) {
            target.convert(order, result);
        }

        end = System.currentTimeMillis();
        System.out.println("testConvert()   :" + String.valueOf(end - start));
    }
}
