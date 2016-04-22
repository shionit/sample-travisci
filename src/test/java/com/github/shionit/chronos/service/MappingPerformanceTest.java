package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.modelmapper.convention.MatchingStrategies;

import java.util.concurrent.TimeUnit;

import static com.github.shionit.chronos.service.MappingServiceTestSupport.createTestOrder;

/**
 * Created by @shionit on 2015/04/15.
 */
public class MappingPerformanceTest {

    private static final int MAX_COUNT = 10000;

    private final ModelMapperService modelmapper = new ModelMapperService();
    private final GetterSetterService getset = new GetterSetterService();

    @Test
    public void testMapPerformance() {
        final Order order = createTestOrder();

        // warm up
        warmUp(order, modelmapper);
        warmUp(order, getset);

        System.out.println("ModelMapper-default()*********");

        performConvert(order, modelmapper);

        modelmapper.prepareMapper(MatchingStrategies.STRICT);
        System.out.println("prepareMapper(STRICT)*********");

        performConvert(order, modelmapper);

        modelmapper.prepareConverter();
        System.out.println("prepareConverter(STRICT)*********");

        performConvert(order, modelmapper);

        System.out.println("simple getter/setter()*********");

        performConvert(order, getset);
    }

    private static final void performConvert(final Order order, final MappingService mapper) {
        OrderDto result;
        Stopwatch stopwatch = Stopwatch.createStarted();

        // create Instance and Convert.
        for (int i = 0; i < MAX_COUNT; i++) {
            result = mapper.convert(order);
        }

        stopwatch.stop();
        System.out.println(mapper.getClass().getSimpleName() + "#convertNew():"
                + String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));

        stopwatch.reset().start();

        result = new OrderDto();
        for (int i = 0; i < MAX_COUNT; i++) {
            mapper.convert(order, result);
        }

        stopwatch.stop();
        System.out.println(mapper.getClass().getSimpleName() + "#convert()   :"
                + String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    private static final void warmUp(final Order order, final MappingService mapper) {
        OrderDto result;

        // create Instance and Convert.
        for (int i = 0; i < MAX_COUNT; i++) {
            result = mapper.convert(order);
        }

        result = new OrderDto();
        for (int i = 0; i < MAX_COUNT; i++) {
            mapper.convert(order, result);
        }
    }
}
