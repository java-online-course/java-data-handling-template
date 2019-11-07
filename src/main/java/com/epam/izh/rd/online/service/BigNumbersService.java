package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BigNumbersService {

    BigDecimal getPrecisionNumber(int a, int b, int range);

    BigInteger getPrimaryNumber(int count);
}
