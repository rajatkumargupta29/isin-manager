package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateUpdateTaskTest {

    @Test
    public void oneLine() {
        String[] parts = new CertificateUpdateTask().call().split(",");
        assertEquals(6, parts.length);

        assertTrue(Long.parseLong(parts[0]) > 0);

        String isin = parts[1];
        assertEquals(12, isin.length());
        int check = new ISINGenerator().calculateCheckDigit(isin.substring(0, 11));
        assertEquals(check, isin.charAt(11) - '0');

        double bidPrice = Double.parseDouble(parts[2]);
        assertTrue(bidPrice >= 100 && bidPrice <= 200);
        double askPrice = Double.parseDouble(parts[4]);
        assertTrue(askPrice >= 100 && askPrice <= 200);

        int bidSize = Integer.parseInt(parts[3]);
        assertTrue(bidSize >= 1000 && bidSize <= 5000);
        int askSize = Integer.parseInt(parts[5]);
        assertTrue(askSize >= 1000 && askSize <= 10000);
    }
}
