package com.github.shionit.chronos.service;

import com.github.shionit.chronos.util.convert.ConverterManager;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by @shionit on 2015/04/22.
 */
public class BulkOrderConverterTest {

    private ConverterManager manager;
    private BulkOrderConverter target = new BulkOrderConverter();

    @Before
    public void setUp() throws Exception {
        ConverterManager.Builder builder = new ConverterManager.Builder(Sets.newHashSet(
                new GetterSetterService(),
                target
        ));
        manager = builder.build();
    }

    @Test
    public void testConvert() throws Exception {
        // TODO:test it
    }

    @Test
    public void testConvert1() throws Exception {
        // TODO:test it
    }
}