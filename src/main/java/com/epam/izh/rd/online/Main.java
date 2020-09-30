package com.epam.izh.rd.online;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BigDecimal bd1 = BigDecimal.valueOf(1);
        BigDecimal bd2 = BigDecimal.valueOf(3);
        BigDecimal result = bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
    }
}
