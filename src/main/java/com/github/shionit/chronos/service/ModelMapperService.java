package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Order;
import com.github.shionit.chronos.model.OrderDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MatchingStrategy;

/**
 * Created by @shionit on 2015/04/10.
 */
public class ModelMapperService implements MappingService {

    private ModelMapper modelMapper = new ModelMapper();

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public OrderDto convert(final Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void convert(final Order order, final OrderDto dto) {
        modelMapper.map(order, dto);
    }

    public void prepareMapper(final MatchingStrategy strategy) {
        modelMapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setBillingCity(source.getBillingAddress().getCity());
                map().setBillingStreet(source.getBillingAddress().getStreet());
                map().setCustomerFirstName(source.getCustomer().getName().getFirstName());
                map().setCustomerLastName(source.getCustomer().getName().getLastName());
            }
        });
        modelMapper.getConfiguration().setMatchingStrategy(strategy);
        modelMapper.validate();
    }

    public void prepareConverter() {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<Order, OrderDto>() {
            @Override
            public OrderDto convert(MappingContext<Order,OrderDto> context) {
                Order source = context.getSource();
                OrderDto dest = context.getDestination();
                if (dest == null) {
                    dest = new OrderDto();
                }
                dest.setBillingCity(source.getBillingAddress().getCity());
                dest.setBillingStreet(source.getBillingAddress().getStreet());
                dest.setCustomerFirstName(source.getCustomer().getName().getFirstName());
                dest.setCustomerLastName(source.getCustomer().getName().getLastName());
                return dest;
            }

            ;
        });
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.validate();
    }
}
