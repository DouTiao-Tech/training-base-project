package com.darcytech.training.core.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T> List<T> filter(Collection<T> unfiltered, Predicate<T> predicate) {
        return unfiltered.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> List<T> reject(Collection<T> unfiltered, Predicate<T> predicate) {
        return filter(unfiltered, predicate.negate());
    }

    public static <F, T> List<T> map(Collection<F> from, Function<? super F, T> mapper) {
        return from.stream().map(mapper).collect(Collectors.toList());
    }

    public static <F, T> List<T> map(F[] from, Function<? super F, T> mapper) {
        return Arrays.stream(from).map(mapper).collect(Collectors.toList());
    }

    public static <K, V, F> Map<K, V> toMap(Collection<F> from, Function<F, K> keyMapper, Function<F, V> valueMapper) {
        return from.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <S> BigDecimal sum(Collection<S> src, Function<S, BigDecimal> valueFn) {
        return src.stream().map(valueFn).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
