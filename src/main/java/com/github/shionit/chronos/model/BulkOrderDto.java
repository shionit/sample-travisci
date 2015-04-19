package com.github.shionit.chronos.model;

import java.util.Date;
import java.util.List;

/**
 * Created by @shionit on 2015/04/19.
 */
public class BulkOrderDto {

    Date orderDate;

    List<OrderDto> orders;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
