package com.example.fx;

public class CaesarCipher {
    final String alphabetEn = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,«»\"':!? ";

    char encryptChar(char character, int key) {
        int index = alphabetEn.indexOf(character);
        if (index != -1) {
            int newIndex = (index + key) % alphabetEn.length();
            return alphabetEn.charAt(newIndex);
        } else {
            return character;
        }
    }

   char decryptChar(char character, int key) {
        int index = alphabetEn.indexOf(character);
        if (index != -1) {
            int newIndex = (index - key + alphabetEn.length()) % alphabetEn.length();
            return alphabetEn.charAt(newIndex);
        } else {
            return character;
        }
    }
}
