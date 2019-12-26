package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        if (b == 0) { //Не знаю правильно ли, но исправляю деление на 0, может нада вернуть "a"?
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), range, RoundingMode.FLOOR);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int primesFound = 0; // Найдено простых цисел
        boolean isPrime = true;
        for (int i = 3; true; i++) {
            // Начинаем с 3, т.к. @param range номер числа, считая с числа 2, т.е. как я понял, 3 -
            // первое, 5 - второе, и т.д.
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    //
                    break;
                }
            }
            if (isPrime) {
                primesFound++;
                if (primesFound == range) {
                    return BigInteger.valueOf(i);
                }
            } else {
                isPrime = true;
            }
        }
    }
}
