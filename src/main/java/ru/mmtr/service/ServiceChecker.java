package ru.mmtr.service;


public class ServiceChecker {

    private static String regKeys;
    private static String regWords;

    static boolean checkWord(String word) {
        return word.matches(regWords);
    }

    static boolean checkKey(String word) {
        return word.matches(regKeys);
    }

    public static void regKeysSet(String keys) {
        regKeys = keys;
    }

    public static void regWordsSet(String words) {
        regWords = words;
    }
}
