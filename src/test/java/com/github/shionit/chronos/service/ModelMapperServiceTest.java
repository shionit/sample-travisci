package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.junit.Test;

import static com.github.shionit.chronos.service.MappingServiceTestSupport.createTestOrder;
import static org.junit.Assert.assertEquals;

/**
 * Created by @shionit on 2015/04/10.
 */
public class ModelMapperServiceTest {

    private final ModelMapperService target = new ModelMapperService();

    @Test
    public void testConvertNew() {
        final Order order = createTestOrder();

        OrderDto result = target.convert(order);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }

    @Test
    public void testConvert() {
        final Order order = createTestOrder();
        OrderDto result = new OrderDto();

        target.convert(order, result);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }
}
