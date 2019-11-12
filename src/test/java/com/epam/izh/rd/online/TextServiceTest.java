package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextService;
import com.epam.izh.rd.online.service.TextService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextServiceTest {

    private static TextService textService;

    @BeforeAll
    static void setup() {
        textService = new SimpleTextService();
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testRemoveStringProvider")
    @DisplayName("Тест метода MathService.removeString(String base, String remove)")
    void testRemoveString(String param1, String param2, String expected) {
        assertEquals(expected, textService.removeString(param1, param2));
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testIsQuestionStringProvider")
    @DisplayName("Тест метода MathService.isQuestionString(String text)")
    void testIsQuestionString(String param, boolean expected) {
        assertEquals(expected, textService.isQuestionString(param));
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testConcatenateProvider")
    @DisplayName("Тест метода MathService.concatenate(String... elements)")
    void testConcatenate(String[] param, String expected) {
        assertEquals(expected, textService.concatenate(param));
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testToJumpCaseProvider")
    @DisplayName("Тест метода MathService.toJumpCase(String text)")
    void testToJumpCase(String param, String expected) {
        assertEquals(expected, textService.toJumpCase(param));
    }

    @ParameterizedTest
    @MethodSource("com.epam.izh.rd.online.Providers#testIsPalindromeProvider")
    @DisplayName("Тест метода MathService.removeString(String base, String remove)")
    void testIsPalindrome(String param, boolean expected) {
        assertEquals(expected, textService.isPalindrome(param));
    }
}
