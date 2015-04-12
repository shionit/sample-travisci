package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * Created by itoshion on 2015/04/10.
 */
public class ModelMapperSample {

    private ModelMapper modelMapper = new ModelMapper();

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public OrderDto convertNew(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public void convert(Order order, OrderDto dto) {
        modelMapper.map(order, dto);
    }

    public void prepareMapper() {
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setBillingCity(source.getBillingAddress().getCity());
                map().setBillingStreet(source.getBillingAddress().getStreet());
                map().setCustomerFirstName(source.getCustomer().getName().getFirstName());
                map().setCustomerLastName(source.getCustomer().getName().getLastName());
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("Hello Java!");
    }
}
