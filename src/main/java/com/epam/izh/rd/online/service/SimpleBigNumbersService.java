package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        return new BigDecimal(a).divide(new BigDecimal(b), range, RoundingMode.HALF_UP);
    }

    public boolean isSimple(int number){
        for (int i = 2; i < (Math.sqrt(number)+2); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int simpleNumber = 2;
        if (range == 0){
            simpleNumber = 0;
        }else
        {
           if (range >= 2){
               simpleNumber = 3;
               for (int i = 2; i < range ; i++) {
                   do {
                       simpleNumber += 2;
                   } while (!isSimple(simpleNumber));
               }
           }
        }
        return BigInteger.valueOf(simpleNumber);
    }
}
