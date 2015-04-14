package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;

/**
 * Created by @shionit on 2015/04/15.
 */
public interface MappingService {
    OrderDto convertNew(Order order);

    void convert(Order order, OrderDto dto);
}
