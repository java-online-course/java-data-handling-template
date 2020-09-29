package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal total = new BigDecimal(a).divide(new BigDecimal(b), range, 1);
        return total;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int size = 1000;
        int lastNumber = range + 1;
        int[] values = new int[size];
        for(int i = 2; i < size; ++i) {
            values[i] = 1;
        }
        int place = 1;
        int p = 2;
        while(place < lastNumber) {
            for(int i = 2 * p; i < size; i += p) {
                values[i] = 0;
            }
            for(int i = p + 1; i < size; ++i) {
                if(values[i] == 1) {
                    p = i;
                    break;
                }
            }
            ++place;
        }
        return BigInteger.valueOf(p);
    }
}
