package com.epam.izh.rd.online.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RegExpService {

    String maskSensitiveData();

    String replacePlaceholders(double paymentAmount, double balance) throws IOException;
}
