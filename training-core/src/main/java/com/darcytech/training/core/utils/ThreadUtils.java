package com.darcytech.training.core.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadUtils {

    public static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
            // do nothing
        }
    }

    public static <T> void waitAllDone(Iterable<? extends Future<? extends T>> futures) throws ExecutionException {
        Throwable ex = null;
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Throwable e) {
                ex = e;
            }
        }
        if (ex instanceof RuntimeException) {
            throw (RuntimeException) ex;
        }
        if (ex instanceof Error) {
            throw (Error) ex;
        }
        if (ex instanceof ExecutionException) {
            throw (ExecutionException) ex;
        }
        if (ex != null) {
            throw new AssertionError(ex);
        }
    }

}
