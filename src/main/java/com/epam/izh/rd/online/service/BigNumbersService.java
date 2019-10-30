package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BigNumbersService {

    /**
     * Метод считает число π с точностью до знака
     * @param range знак
     * @return число π
     */
    BigDecimal getPiWithScale(int range);

    /**
     * Метод находит простое число
     * @param range номер числа, считая с числа 1
     * @return простое число
     */
    BigInteger getPrimaryNumber(int range);
}
