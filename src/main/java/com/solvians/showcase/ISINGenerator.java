package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class ISINGenerator {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHANUMERIC ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder isin = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            isin.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }

        for (int i = 0; i < 9; i++) {
            isin.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        return isin.toString() + calculateCheckDigit(isin.toString());
    }

    public int calculateCheckDigit(String isin) {
        if (isin == null || isin.length() != 11) {
            throw new IllegalArgumentException("ISIN must be 11 characters");
        }

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < isin.length(); i++) {
            char c = isin.charAt(i);
            if (c >= '0' && c <= '9') {
                digits.append(c);
            } else if (c >= 'A' && c <= 'Z') {
                digits.append(c - 'A' + 10);
            } else {
                throw new IllegalArgumentException("ISIN must be uppercase letters and digits");
            }
        }

        int sum = 0;
        boolean multiply = true;

        for (int i = digits.length() - 1; i >= 0; i--) {
            int number = digits.charAt(i) - '0';
            if (multiply) {
                number = number * 2;
            }
            if (number > 9) {
                sum = sum + number / 10 + number % 10;
            } else {
                sum = sum + number;
            }
            multiply = !multiply;
        }

        if (sum % 10 == 0) {
            return 0;
        }
        return 10 - (sum % 10);
    }
}