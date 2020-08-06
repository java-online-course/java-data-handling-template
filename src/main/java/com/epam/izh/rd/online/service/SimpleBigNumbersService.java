package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 2, RoundingMode.HALF_UP);
    }

    @Override
    public BigInteger getPrimaryNumber(int range) {
        return primaryNumber(range);
    }

    private BigInteger primaryNumber(int numberIndex) {
        BigInteger number = BigInteger.valueOf(2);
        while(numberIndex-- != 0) {
            number = number.nextProbablePrime();
        }
        return number;
    }
}
