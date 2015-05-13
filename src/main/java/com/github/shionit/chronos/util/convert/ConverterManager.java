package com.github.shionit.chronos.util.convert;

import org.modelmapper.internal.util.TypeResolver;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by @shionit on 2015/04/19.
 */
public final class ConverterManager {

    private static final String DEFAULT_CONVERTER_NAME = "default";

    @SuppressWarnings("rawtypes")
    private final ConcurrentHashMap<ConverterCacheKey, Converter> cache = new ConcurrentHashMap<>();

    ConverterManager(Set<Converter> converters) {
        converters.forEach(this::addConverter);
    }

    private <S, D> void addConverter(final Converter<S, D> converter) {
        addConverter(converter, null);
    }

    private <S, D> void addConverter(final Converter<S, D> converter, String name) {
        Objects.requireNonNull(converter, "converter");
        if (name == null || name.isEmpty()) {
            name = DEFAULT_CONVERTER_NAME;
        }
        final Class<?>[] typeArguments = TypeResolver.resolveArguments(converter.getClass(), Converter.class);
        final ConverterCacheKey key = new ConverterCacheKey(typeArguments[0], typeArguments[1], name);

        if (null != cache.putIfAbsent(key, converter)) {
            throw new IllegalStateException("converter key is already exists." + key.toString());
        }
        if (converter instanceof ConverterManagerAware) {
            ((ConverterManagerAware) converter).setConverterManager(this);
        }
    }

    public final <S, D> Converter<S, D> getConverter(final Class<S> sourceClass, final Class<D> destClass) {
        return getConverter(sourceClass, destClass, DEFAULT_CONVERTER_NAME);
    }

    @SuppressWarnings("unchecked")
    public final <S, D> Converter<S, D> getConverter(final Class<S> sourceClass, final Class<D> destClass, final String name) {
        Objects.requireNonNull(sourceClass, "sourceClass");
        Objects.requireNonNull(destClass, "destClass");
        Objects.requireNonNull(name, "name");
        final ConverterCacheKey key = new ConverterCacheKey(sourceClass, destClass, name);
        return cache.get(key);
    }

    public final <S, D> Converter<S, D> getConverter(final Class<? extends Converter<S, D>> converterClass) {
        Objects.requireNonNull(converterClass, "converterClass");
        final Class<?>[] typeArguments = TypeResolver.resolveArguments(converterClass, Converter.class);
        final ConverterCacheKey key = new ConverterCacheKey(typeArguments[0], typeArguments[1], DEFAULT_CONVERTER_NAME);
        return cache.get(key);
    }

    @SuppressWarnings("serial")
    private class ConverterCacheKey implements Serializable {
        private final Class<?> sourceClass;
        private final Class<?> destClass;
        private final String name;

        public ConverterCacheKey(Class<?> sourceClass, Class<?> destClass, String name) {
            this.sourceClass = sourceClass;
            this.destClass = destClass;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ConverterCacheKey[" +
                    "sourceClass=" + sourceClass +
                    ",destClass=" + destClass +
                    ",name='" + name + '\'' +
                    ']';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ConverterCacheKey that = (ConverterCacheKey) o;

            if (!sourceClass.equals(that.sourceClass)) return false;
            if (!destClass.equals(that.destClass)) return false;
            return name.equals(that.name);

        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceClass, destClass, name);
        }
    }

    public static class Builder {
        private ConverterManager instance;

        public Builder(Set<Converter> converters) {
            instance = new ConverterManager(converters);
        }

        public void addConverter(Converter converter) {
            instance.addConverter(converter);
        }

        public void addConverter(Converter converter, String name) {
            instance.addConverter(converter, name);
        }

        public ConverterManager build() {
            return instance;
        }
    }
}
