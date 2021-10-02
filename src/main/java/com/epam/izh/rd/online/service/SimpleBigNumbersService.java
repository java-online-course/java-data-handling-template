package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

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
        BigDecimal bd3 = bd1.divide(bd2, 2, RoundingMode.DOWN);
        return bd3;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        ArrayList<Integer> primeNums = new ArrayList<>();
        int i =0;
        int num =0;
        for (i = 1; i <= 1000; i++) {
            int counter = 0;
            for (num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                }
            }
            if (counter == 2) {
                primeNums.add(i);
            }
        }

        return BigInteger.valueOf(primeNums.get(range));
    }
}
