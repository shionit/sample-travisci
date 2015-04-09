package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.modelmapper.ModelMapper;

/**
 * Created by itoshion on 2015/04/10.
 */
public class ModelMapperSample {

    public OrderDto convert(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrderDto.class);
    }
}
