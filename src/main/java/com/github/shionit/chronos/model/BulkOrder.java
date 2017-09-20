package com.github.shionit.chronos.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by @shionit on 2015/04/19.
 */
@Data
public class BulkOrder {

    private LocalDate orderDate;

    private List<Order> orders;

}
