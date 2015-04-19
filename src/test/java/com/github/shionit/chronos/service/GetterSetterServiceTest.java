package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.junit.Test;

import static com.github.shionit.chronos.service.MappingServiceTestSupport.createTestOrder;
import static org.junit.Assert.assertEquals;

/**
 * Created by @shionit on 2015/04/15.
 */
public class GetterSetterServiceTest {

    private final GetterSetterService getset = new GetterSetterService();

    @Test
    public void testConvertNew() throws Exception {
        final Order order = createTestOrder();

        OrderDto result = getset.convert(order);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }

    @Test
    public void testConvert() throws Exception {
        final Order order = createTestOrder();
        OrderDto result = new OrderDto();

        getset.convert(order, result);

        assertEquals(order.getCustomer().getName().getFirstName(), result.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), result.getCustomerLastName());
        assertEquals(order.getBillingAddress().getStreet(), result.getBillingStreet());
        assertEquals(order.getBillingAddress().getCity(), result.getBillingCity());
    }
}