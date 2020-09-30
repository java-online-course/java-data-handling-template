package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal bd1 = BigDecimal.valueOf(a);
        BigDecimal bd2 = BigDecimal.valueOf(b);
        BigDecimal result = bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
        return result;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        if (range == 1){
            return BigInteger.valueOf(2);
        }
        BigInteger bi  =  BigInteger.valueOf(3);
        int count = 0;
        while (true){
            if (bi.isProbablePrime(100)){
                count++;
            }
            if (count == range){
                return bi;
            }
            bi = bi.add(BigInteger.valueOf(2));
        }
    }
}
