package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.city.SortOrder;
import org.springframework.core.convert.converter.Converter;

public class SortOrderConverter implements Converter<String, SortOrder> {
    @Override
    public SortOrder convert(String source) {
        try {
            return SortOrder.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
