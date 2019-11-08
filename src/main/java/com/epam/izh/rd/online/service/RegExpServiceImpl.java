package com.epam.izh.rd.online.service;

public class RegExpServiceImpl implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt и маскировать в нем конфиденциальную информацию. Например, номер счета
     * должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен создержать регулярное
     * выражение для поиска счета
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        return null;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double amount1, double amount2) {
        return null;
    }
}
