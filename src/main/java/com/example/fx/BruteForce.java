package com.example.fx;

import java.io.*;

public class BruteForce {
    void bruteForce(String filePath) throws IOException {
        CaesarCipher caesarCipher = new CaesarCipher();
        String alphabet = caesarCipher.alphabetEn;
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            byte[] encryptedBytes = readBytesFromFile(inputStream);

            int bestKey = -1;
            double bestScore = Double.MIN_VALUE;

            for (int key = 0; key < alphabet.length(); key++) {
                byte[] decryptedBytes = decryptBytes(encryptedBytes, key);
                String decryptedText = new String(decryptedBytes);

                double score = calculateScore(decryptedText);

                if (score > bestScore) {
                    bestScore = score;
                    bestKey = key;
                }
            }

            System.out.println("Best Key: " + bestKey);

            inputStream.close();
            inputStream = new FileInputStream(filePath);
            outputStream = new FileOutputStream(filePath + "[DECRYPTED_BY_KEY_" + bestKey + "]");
            byte[] decryptedBytes = decryptBytes(readBytesFromFile(inputStream), bestKey);
            outputStream.write(decryptedBytes);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private byte[] readBytesFromFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        byte[] data = new byte[1024];

        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }

    private byte[] decryptBytes(byte[] data, int key) {
        byte[] decryptedBytes = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            decryptedBytes[i] = (byte) (data[i] - key);
        }
        return decryptedBytes;
    }

    private double calculateScore(String text) {
        int eCount = 0;
        for (char c : text.toCharArray()) {
            if (c == 'e' || c == 'E') {
                eCount++;
            }
        }
        return eCount * 1.0 / text.length();
    }
}