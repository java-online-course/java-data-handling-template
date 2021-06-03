package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.StrictMath.sqrt;

public class SimpleBigNumbersService implements BigNumbersService {


    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return new BigDecimal(a).divide(new BigDecimal(b), range, RoundingMode.HALF_UP);
    }


    @Override
    public BigInteger getPrimaryNumber(int range) {
        int i = 0;
        BigInteger number = new BigInteger("1");
        while (true) {
            if (number.isProbablePrime(range)) {
                if (i == range) {
                    return number;
                }
                i++;
            }
            number = number.add(new BigInteger("1"));
        }
    }


}
