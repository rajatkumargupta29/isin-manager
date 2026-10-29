package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<CertificateUpdate> updateList = new ArrayList<CertificateUpdate>();
        List<CertificateUpdateTask> tasks = new ArrayList<CertificateUpdateTask>();
        List<Future<String>> futures = new ArrayList<Future<String>>();

        try {
            for (int i = 0; i < threads * quotes; i++) {
                CertificateUpdateTask task = new CertificateUpdateTask();
                tasks.add(task);
                futures.add(executor.submit(task));
            }
            for (int i = 0; i < futures.size(); i++) {
                futures.get(i).get();
                updateList.add(tasks.get(i).getUpdate());
            }
            return updateList.stream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }
}
