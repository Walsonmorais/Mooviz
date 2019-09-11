package com.buka.mooviz.Utilities;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final Executor networkExecutor = Executors.newFixedThreadPool(4);
    private static final Executor diskExecutor = Executors.newSingleThreadExecutor();

    public static Executor getNetworkIO() {
        return networkExecutor;
    }

    public static Executor getDiskIO() {
        return diskExecutor;
    }
}

