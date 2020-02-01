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
        return new BigDecimal(a).divide(new BigDecimal(b),range,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int count = 0;
        int simpleNumber = 2;
        boolean isSimple = true;

        while (count<range){
            simpleNumber++;
                for (int i = 2; i<=simpleNumber/2;i++){
                    int simple = simpleNumber % i;
                    if (simple==0){
                        isSimple = false;
                        break;
                    }
                }
                if(isSimple){
                    count++;
                }
            isSimple=true;
        }
        return BigInteger.valueOf(simpleNumber);
    }
}
