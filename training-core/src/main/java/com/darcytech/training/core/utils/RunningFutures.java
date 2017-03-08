package com.darcytech.training.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.function.Function;

import com.google.common.collect.Maps;

public class RunningFutures<K> {

    private ConcurrentMap<K, Future<?>> registeredFutures = Maps.newConcurrentMap();

    public void register(K key, Future<?> f) {
        registeredFutures.put(key, f);
    }

    public List<K> cleanAndRejectRunning(Collection<K> values) {
        return cleanAndRejectRunning(values, Function.<K>identity());
    }

    public <V> List<V> cleanAndRejectRunning(Collection<V> values, Function<V, K> keyFn) {
        registeredFutures.forEach((k, f) -> {
            if (f.isDone()) {
                registeredFutures.remove(k);
            }
        });
        return Streams2.reject(values, v->{
            K key = keyFn.apply(v);
            Future<?> f = registeredFutures.get(key);
            return f != null && f.isDone() == false;
        });
    }

}
