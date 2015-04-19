package com.github.shionit.chronos.util.convert;

import org.modelmapper.internal.util.Assert;
import org.modelmapper.internal.util.TypeResolver;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by @shionit on 2015/04/19.
 */
public class ConverterManager {

    private static final ConverterManager INSTANCE = new ConverterManager();
    private static final Map<ConverterCacheKey, Converter> cache = new ConcurrentHashMap<>();
    private static final String DEFAULT_CONVERTER_NAME = "default";

    private ConverterManager() {
    }

    public static ConverterManager getInstance() {
        return INSTANCE;
    }

    public final <S, D> void addConverter(final Converter<S, D> converter) {
        addConverter(converter, null);
    }

    public final <S, D> void addConverter(final Converter<S, D> converter, String name) {
        Assert.notNull(converter, "converter");
        if (name == null || name.isEmpty()) {
            name = DEFAULT_CONVERTER_NAME;
        }
        Class<?>[] typeArguments = TypeResolver.resolveArguments(converter.getClass(), Converter.class);
        ConverterCacheKey key = new ConverterCacheKey(typeArguments[0], typeArguments[1], name);

        if (null != cache.putIfAbsent(key, converter)) {
            throw new IllegalStateException("converter key is already exists." + key.toString());
        }
    }

    public final Converter<?, ?> getConverter(final Class<?> sourceClass, final Class<?> destClass) {
        return getConverter(sourceClass, destClass, DEFAULT_CONVERTER_NAME);
    }

    public final Converter<?, ?> getConverter(final Class<?> sourceClass, final Class<?> destClass, final String name) {
        Assert.notNull(name, "name");
        ConverterCacheKey key = new ConverterCacheKey(sourceClass, destClass, name);
        return cache.get(key);
    }

    private class ConverterCacheKey implements Serializable {
        private final Class<?> sourceClass;
        private final Class<?> destClass;
        private final String name;

        public ConverterCacheKey(Class<?> sourceClass, Class<?> destClass, String name) {
            this.sourceClass = sourceClass;
            this.destClass = destClass;
            this.name = name;
        }

        public Class<?> getSourceClass() {
            return sourceClass;
        }

        public Class<?> getDestClass() {
            return destClass;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "ConverterCacheKey{" +
                    "sourceClass=" + sourceClass +
                    ", destClass=" + destClass +
                    ", name='" + name + '\'' +
                    '}';
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
            int result = sourceClass.hashCode();
            result = 31 * result + destClass.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }
}
