package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.DateService;
import com.epam.izh.rd.online.service.SimpleDateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateServiceTest {

    private static DateService dateService;

    @BeforeAll
    static void setup() {
        dateService = new SimpleDateService();
    }

    @Test
    @DisplayName("Тест метода DateService.parseDate(LocalDate localDate)")
    void testParseDate() {
        assertEquals("30-01-2020", dateService.parseDate(of(2020, 1, 30)));
    }

    @Test
    @DisplayName("Тест метода DateService.parseString(String string)")
    void testParseString() {
        assertEquals(of(1970, 1, 1).atStartOfDay(), dateService.parseString("1970-01-01 00:00"));
    }


    @Test
    @DisplayName("Тест метода DateService.convertToCustomFormat(String string, DateTimeFormatter formatter)")
    void testConvertToAnotherFormat() {
        assertEquals("2011-12-03", dateService.convertToCustomFormat(of(2011, 12, 3), DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    @DisplayName("Тест метода DateService.getNextLeapYear()")
    void testGetNextLeapYear() {
        long year = IntStream.range(now().getYear(), now().getYear() + 8)
                .filter(Year::isLeap)
                .findFirst()
                .getAsInt();

        assertEquals(year, dateService.getNextLeapYear());
    }

    @Test
    @DisplayName("Тест метода DateService.getSecondsInYear()")
    void testGetSecondsInYear() {
        assertEquals(
            ChronoUnit.SECONDS.between(
                of(2020, 1, 1).atStartOfDay(),
                of(2021, 1,1).atStartOfDay()),
            dateService.getSecondsInYear(2020)
        );
    }

}
