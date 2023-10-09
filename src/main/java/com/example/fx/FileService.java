package com.example.fx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileService {
    protected void encryptFile(String filePath, int key) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            outputStream = new FileOutputStream(filePath + "[ENCRYPTED]");

            int bytesRead;
            while ((bytesRead = inputStream.read()) != -1) {
                char originalChar = (char) bytesRead;
                CaesarCipher caesarCipher = new CaesarCipher();
                char encryptedChar = caesarCipher.encryptChar(originalChar, key);
                outputStream.write((int) encryptedChar);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    protected void decryptFile(String filePath, int key) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            outputStream = new FileOutputStream(filePath + "[DECRYPTED]");

            int bytesRead;
            while ((bytesRead = inputStream.read()) != -1) {
                char encryptedChar = (char) bytesRead;
                CaesarCipher caesarCipher = new CaesarCipher();
                char decryptedChar = caesarCipher.decryptChar(encryptedChar, key);
                outputStream.write((int) decryptedChar);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
