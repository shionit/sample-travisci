package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Address;
import com.github.shionit.chronos.model.Customer;
import com.github.shionit.chronos.model.Name;
import com.github.shionit.chronos.model.Order;

/**
 * Created by @shionit on 2015/04/15.
 */
public class MappingServiceTestSupport {

    public static final Order createTestOrder() {
        final Name name = new Name();
        name.setFirstName("Taro");
        name.setLastName("Yamada");
        final Customer customer = new Customer();
        customer.setName(name);
        final Address address = new Address();
        address.setCity("Tokyo");
        address.setStreet("Central-Way");
        final Order order = new Order();
        order.setBillingAddress(address);
        order.setCustomer(customer);
        return order;
    }

}
