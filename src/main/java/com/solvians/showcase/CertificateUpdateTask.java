package com.solvians.showcase;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdateTask implements Callable<String> {

    private CertificateUpdate update;

    @Override
    public String call() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int bid = random.nextInt(10000, 20001);
        int ask = random.nextInt(10000, 20001);
        int bidSize = random.nextInt(1000, 5001);
        int askSize = random.nextInt(1000, 10001);

        update = new CertificateUpdate(
                System.currentTimeMillis(),
                new ISINGenerator().generate(),
                bid / 100.0,
                bidSize,
                ask / 100.0,
                askSize);
        return update.toString();
    }

    CertificateUpdate getUpdate() {
        return update;
    }
}
