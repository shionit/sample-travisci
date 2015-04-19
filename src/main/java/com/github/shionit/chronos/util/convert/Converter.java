package com.github.shionit.chronos.util.convert;

/**
 * Created by @shionit on 2015/04/18.
 *
 * @param <S> Source Class
 * @param <D> Destination Class
 */
public interface Converter<S, D> {

    void convert(S src, D dest);

    D convert(S src);

}
