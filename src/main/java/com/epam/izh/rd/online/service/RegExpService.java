package com.epam.izh.rd.online.service;

public interface RegExpService {

    String maskSensitiveData();

    String replacePlaceholders(double amount1, double amount2);
}
