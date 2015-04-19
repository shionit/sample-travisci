package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;

/**
 * Created by @shionit on 2015/04/14.
 */
public class GetterSetterService implements MappingService {

    @Override
    public final OrderDto convert(final Order order) {
        OrderDto dto = new OrderDto();
        dto.setBillingCity(order.getBillingAddress().getCity());
        dto.setBillingStreet(order.getBillingAddress().getStreet());
        dto.setCustomerFirstName(order.getCustomer().getName().getFirstName());
        dto.setCustomerLastName(order.getCustomer().getName().getLastName());
        return dto;
    }

    @Override
    public final void convert(final Order order, final OrderDto dto) {
        dto.setBillingCity(order.getBillingAddress().getCity());
        dto.setBillingStreet(order.getBillingAddress().getStreet());
        dto.setCustomerFirstName(order.getCustomer().getName().getFirstName());
        dto.setCustomerLastName(order.getCustomer().getName().getLastName());
    }
}
