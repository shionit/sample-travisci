package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.BulkOrder;
import com.github.shionit.chronos.model.BulkOrderDto;
import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import com.github.shionit.chronos.util.convert.Converter;
import com.github.shionit.chronos.util.convert.ConverterManager;
import com.github.shionit.chronos.util.convert.ConverterManagerAware;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by @shionit on 2015/04/19.
 */
public class BulkOrderConverter implements Converter<BulkOrder, BulkOrderDto>, ConverterManagerAware {

    private ConverterManager manager;

    private Converter<Order, OrderDto> orderConverter;

    @Override
    public void setConverterManager(ConverterManager manager) {
        this.manager = manager;
    }

    @Override
    public void convert(BulkOrder src, BulkOrderDto dest) {
        if (orderConverter == null) {
            orderConverter = manager.getConverter(Order.class, OrderDto.class);
        }
        dest.setOrderDate(new Date(src.getOrderDate().toEpochDay()));
        List<OrderDto> orders = Lists.newArrayList();
        for (Order order : src.getOrders()) {
            OrderDto dto = orderConverter.convert(order);
            orders.add(dto);
        }
        dest.setOrders(orders);
    }

    @Override
    public BulkOrderDto convert(BulkOrder src) {
        BulkOrderDto dest = new BulkOrderDto();
        convert(src, dest);
        return dest;
    }
}
