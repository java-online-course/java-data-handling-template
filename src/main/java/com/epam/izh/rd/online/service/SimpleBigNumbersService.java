package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.divide(bigDecimalB,range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
            int primaryNumber = 0;

            List<Integer> primes = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                    primaryNumber++;
                    if (primaryNumber == range + 1){
                        primaryNumber = i;
                        break;
                    }
                }
            }
            return BigInteger.valueOf(primaryNumber);
        }

        private boolean isPrime(int n) {
            if (n == 2 || n == 3) {
                return true;
            } else if (n <= 1 || (n % 2) == 0 || (n % 3) == 0) {
                return false;
            }
            int i = 5;
            while (i * i <= n) {
                if ((n % i) == 0 || (n % (i + 2)) == 0) {
                    return false;
                }
                i += 6;
            }
            return true;
        }
    }
