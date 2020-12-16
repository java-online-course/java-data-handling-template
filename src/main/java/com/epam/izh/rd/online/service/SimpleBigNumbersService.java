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
        BigDecimal bd = new BigDecimal((double)a/b);
        return bd.setScale(range, bd.ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger n= BigInteger.valueOf(2);
        BigInteger rez = n;
        for(int i = 0; i<=range; i++){
            boolean f= true;
            while (f) {
                boolean f2=true;
                for (int j = 2; j < n.intValue(); j++) {
                    if (n.intValue() % j == 0) {
                        f2=false;
                        break;
                    }
                }
                if(f2){
                    f=false;
                    rez = n;
                }
                BigInteger b = new BigInteger("1");
                n=new BigInteger(String.valueOf(n.add(b)));
            }
        }
        return rez;
    }
}
