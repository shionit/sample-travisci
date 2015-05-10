package com.github.shionit.chronos.util.convert;

/**
 * Created by @shionit on 2015/05/09.
 */
public interface ConverterManager {
    <S, D> Converter<S, D> getConverter(Class<S> sourceClass, Class<D> destClass);

    @SuppressWarnings("unchecked")
    <S, D> Converter<S, D> getConverter(Class<S> sourceClass, Class<D> destClass, String name);

    <S, D> Converter<S, D> getConverter(Class<? extends Converter<S, D>> converterClass);
}
