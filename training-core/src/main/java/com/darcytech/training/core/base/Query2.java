package com.darcytech.training.core.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;

public abstract class Query2 {

    public static final int[] IN_QUERY_SIZES = {1, 3, 10, 50};

    public static final int MAX_IN_QUERY_SIZE = IN_QUERY_SIZES[IN_QUERY_SIZES.length - 1];

    public static <E> Iterable<List<E>> partitionForQuery(Iterable<E> iterable) {
        int maxPartSize = MAX_IN_QUERY_SIZE;
        int padSize = padSize(Iterables.size(iterable) % maxPartSize);

        if (padSize > 0) {
            E lastElement = Iterables.getLast(iterable);
            List<E> nCopies = Collections.nCopies(padSize, lastElement);
            iterable = Iterables.concat(iterable, nCopies);
        }
        return Iterables.partition(iterable, maxPartSize);
    }

    public static <E> Iterable<E> padForQuery(Iterable<E> iterable) {
        int size = Iterables.size(iterable);
        int padSize = padSize(size);
        return padSize == 0 ? iterable : Iterables.concat(iterable, Collections.nCopies(padSize, Iterables.getLast(iterable)));
    }

    public static int sizeAfterPadForQuery(int size) {
        return size + padSize(size % MAX_IN_QUERY_SIZE);
    }

    public static int padSize(int size) {
        if (size <= 0 || size >= MAX_IN_QUERY_SIZE) {
            return 0;
        }
        return Arrays.stream(IN_QUERY_SIZES).filter(n -> n >= size).findFirst().orElse(size) - size;
    }

}
