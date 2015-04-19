package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.BulkOrder;
import com.github.shionit.chronos.model.BulkOrderDto;
import com.github.shionit.chronos.util.convert.Converter;

import java.util.Date;

/**
 * Created by @shionit on 2015/04/19.
 */
public class BulkOrderConverter implements Converter<BulkOrder, BulkOrderDto> {

    @Override
    public void convert(BulkOrder src, BulkOrderDto dest) {
        dest.setOrderDate(new Date(src.getOrderDate().toEpochDay()));
        // TODO: convert orders
    }

    @Override
    public BulkOrderDto convert(BulkOrder src) {
        BulkOrderDto dest;
        try {
            dest = BulkOrderDto.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        convert(src, dest);
        return dest;
    }
}
