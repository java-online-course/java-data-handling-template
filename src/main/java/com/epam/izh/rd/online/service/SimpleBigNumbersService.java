package com.epam.izh.rd.online.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {

        double rez = (double)a/(double)b;
        BigDecimal bigDecimal = new BigDecimal(rez);
        bigDecimal = bigDecimal.setScale(range, RoundingMode.DOWN);

        return bigDecimal;
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
       int[] prime = IntStream.iterate(2, i -> i + 1)
               .filter(j -> IntStream.range(2, j).noneMatch(e ->  j % e == 0))
               .limit(size)
               .toArray();
        int rez = prime[range];
       return BigInteger.valueOf(rez);
    }
}
