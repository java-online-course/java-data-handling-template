package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * @param range точность
     * @return результат
     */
    BigDecimal getPiWithScale(int a, int b, int range);

    /**
     * Метод находит простое число
     * @param range номер числа, считая с числа 1
     * @return простое число
     */
    BigInteger getPrimaryNumber(int range);
}
