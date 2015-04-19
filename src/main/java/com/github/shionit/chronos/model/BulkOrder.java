package com.github.shionit.chronos.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by @shionit on 2015/04/19.
 */
public class BulkOrder {

    private LocalDate orderDate;

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

}
