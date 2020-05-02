package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal dividend = BigDecimal.valueOf(a);
        BigDecimal divider = BigDecimal.valueOf(b);
        return dividend.divide(divider, range, BigDecimal.ROUND_DOWN);
    }

    @Override
    public BigInteger getPrimaryNumber(int range) {
        int count = 0;
        int currentValue = 2;
        while (count != range) {
            boolean isSimpleNumber = true;
            currentValue++;
            for (int i = 2; i < currentValue; i++) {
                if (currentValue % i == 0) {
                    isSimpleNumber = false;
                    break;
                }
            }
            if (isSimpleNumber) {
                count++;
            }
        }
        return BigInteger.valueOf(currentValue);
    }
}
