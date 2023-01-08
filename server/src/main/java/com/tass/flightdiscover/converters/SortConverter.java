package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.city.Sort;
import org.springframework.core.convert.converter.Converter;

public class SortConverter implements Converter<String, Sort> {

    @Override
    public Sort convert(String source) {
        try {
            return Sort.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
