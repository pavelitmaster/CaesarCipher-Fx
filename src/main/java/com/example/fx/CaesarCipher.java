package com.example.fx;

public class CaesarCipher {
    final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,«»\"':!? ";

    protected char encryptChar(char character, int key) {
        int index = ALPHABET.indexOf(character);
        if (index != -1) {
            int newIndex = (index + key) % ALPHABET.length();
            return ALPHABET.charAt(newIndex);
        } else {
            return character;
        }
    }

    protected char decryptChar(char character, int key) {
        int index = ALPHABET.indexOf(character);
        if (index != -1) {
            int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
            return ALPHABET.charAt(newIndex);
        } else {
            return character;
        }
    }
}
