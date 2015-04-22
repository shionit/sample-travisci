package com.github.shionit.chronos.util.convert;

import com.github.shionit.chronos.model.Customer;
import com.github.shionit.chronos.model.Name;
import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by @shionit on 2015/04/20.
 */
public class ConverterManagerTest {

    @Before
    public void setUp() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        manager.clear();
    }

    @Test
    public void testGetInstance() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        assertNotNull(manager);
    }

    @Test
    public void testAddConverter_normal_defaultName() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        MockConverterA converter = new MockConverterA();

        manager.addConverter(converter);

        Converter result = manager.getConverter(Customer.class, Name.class);
        assertNotNull(result);
        assertEquals(converter, result);
    }

    @Test
    public void testAddConverter_normal_testName() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        MockConverterA converter = new MockConverterA();

        manager.addConverter(converter, "testConverter");

        Converter result = manager.getConverter(Customer.class, Name.class, "testConverter");
        assertNotNull(result);
        assertEquals(converter, result);
    }

    @Test(expected = NullPointerException.class)
    public void testAddConverter_converter_isNull_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();

        manager.addConverter(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddConverter_converter_duplicate_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        MockConverterA converter1 = new MockConverterA();
        MockConverterB converter2 = new MockConverterB();

        manager.addConverter(converter1);
        manager.addConverter(converter2);
    }

    @Test
    public void testAddConverter_normal_different_name() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        MockConverterA converter1 = new MockConverterA();
        MockConverterB converter2 = new MockConverterB();

        manager.addConverter(converter1, "converterA");
        manager.addConverter(converter2, "converterB");

        Converter resultA = manager.getConverter(Customer.class, Name.class, "converterA");
        assertNotNull(resultA);
        assertEquals(converter1, resultA);
        Converter resultB = manager.getConverter(Customer.class, Name.class, "converterB");
        assertNotNull(resultB);
        assertEquals(converter2, resultB);
    }

    @Test
    public void testAddConverter_normal_different_class() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        Converter<Customer, Name> converter1 = new MockConverterA();
        Converter<Order, OrderDto> converter2 = new MockConverterC();

        manager.addConverter(converter1);
        manager.addConverter(converter2);

        Converter<Customer, Name> resultA = manager.getConverter(Customer.class, Name.class);
        assertNotNull(resultA);
        assertEquals(converter1, resultA);
        Converter<Order, OrderDto> resultB = manager.getConverter(Order.class, OrderDto.class);
        assertNotNull(resultB);
        assertEquals(converter2, resultB);
    }

    @Test(expected = NullPointerException.class)
    public void testGetConverter_sourceClass_isNull_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        manager.getConverter(null, Name.class);
    }

    @Test(expected = NullPointerException.class)
    public void testGetConverter_destClass_isNull_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        manager.getConverter(Customer.class, null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetConverter_name_isNull_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        manager.getConverter(Customer.class, Name.class, null);
    }

    @Test
    public void testGetConverter_another_converter() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        Converter<Customer, Name> converter1 = new MockConverterA();

        manager.addConverter(converter1);

        Converter<Order, OrderDto> result = manager.getConverter(Order.class, OrderDto.class);
        assertNull(result);
    }

    @Test
    public void testGetConverter_normal_default_get_by_class() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();
        Converter<Customer, Name> converter1 = new MockConverterA();

        manager.addConverter(converter1);

        Converter<Customer, Name> result = manager.getConverter(MockConverterA.class);
        assertNotNull(result);
        assertEquals(converter1, result);
    }

    @Test
    public void testGetConverter_not_registered_default_get_by_class() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();

        Converter<Customer, Name> result = manager.getConverter(MockConverterA.class);
        assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetConverter_class_is_null_error() throws Exception {
        ConverterManager manager = ConverterManager.getInstance();

        manager.getConverter(null);
    }

    public static class MockConverterA implements Converter<Customer, Name> {
        @Override
        public void convert(Customer src, Name dest) {
        }

        @Override
        public Name convert(Customer src) {
            return null;
        }
    }

    public static class MockConverterB implements Converter<Customer, Name> {
        @Override
        public void convert(Customer src, Name dest) {
        }

        @Override
        public Name convert(Customer src) {
            return null;
        }
    }

    public static class MockConverterC implements Converter<Order, OrderDto> {
        @Override
        public void convert(Order src, OrderDto dest) {
        }

        @Override
        public OrderDto convert(Order src) {
            return null;
        }
    }
}