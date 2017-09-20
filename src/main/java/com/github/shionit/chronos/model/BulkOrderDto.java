package com.github.shionit.chronos.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by @shionit on 2015/04/19.
 */
@Data
public class BulkOrderDto {

    Date orderDate;

    List<OrderDto> orders;
}
