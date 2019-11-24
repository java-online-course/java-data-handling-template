package com.epam.izh.rd.online;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

    private Providers() {

    }

    public static Stream<Arguments> testRemoveStringProvider() {
        return Stream.of(
                arguments("Hello, hello, hello, how low?", ", he", "Hellollollo, how low?"),
                arguments("Ahahahahaha", "ha", "A"),
                arguments("London is the capital of Great Britain", "Russia", "London is the capital of Great Britain")
        );
    }

    public static Stream<Arguments> testIsQuestionStringProvider() {
        return Stream.of(
                arguments("Hello, hello, hello, how low?", true),
                arguments("Hello, hello, hello!", false),
                arguments("", false)
        );
    }

    public static Stream<Arguments> testConcatenateProvider() {
        return Stream.of(
                arguments(new String[]{"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}, "Smells Like Teen Spirit"),
                arguments(new String[]{"", ""}, "")
        );
    }

    public static Stream<Arguments> testToJumpCaseProvider() {
        return Stream.of(
                arguments("Load Up On Guns And Bring Your Friends", "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS"),
                arguments("", "")
        );
    }

    public static Stream<Arguments> testIsPalindromeProvider() {
        return Stream.of(
                arguments("а роза упала на лапу Азора", true),
                arguments("я не палиндром!", false),
                arguments("", false)
        );
    }
}
