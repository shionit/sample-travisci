package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import com.github.shionit.chronos.util.convert.Converter;

/**
 * Created by @shionit on 2015/04/15.
 */
public interface MappingService extends Converter<Order, OrderDto> {
    OrderDto convert(Order order);

    void convert(Order order, OrderDto dto);
}
