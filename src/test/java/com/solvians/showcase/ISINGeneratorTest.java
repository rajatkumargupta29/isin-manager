package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ISINGeneratorTest {

    private final ISINGenerator generator = new ISINGenerator();

    @Test
    public void checkDigit() {
        assertEquals(6, generator.calculateCheckDigit("DE123456789"));
    }

    @Test
    public void generateIsin() {
        String isin = generator.generate();
        assertEquals(12, isin.length());
        int check = generator.calculateCheckDigit(isin.substring(0, 11));
        assertEquals(check, isin.charAt(11) - '0');
    }
}
