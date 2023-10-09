package com.example.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Runner extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Caesar Cipher");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label commandLabel = new Label("Enter command (ENCRYPT/DECRYPT/BRUTE_FORCE):");
        TextField commandField = new TextField();

        Label filePathLabel = new Label("Enter input file path:");
        TextField filePathField = new TextField();

        Label keyLabel = new Label("Enter key (for ENCRYPT/DECRYPT):");
        TextField keyField = new TextField();

        Button executeButton = new Button("Execute");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        executeButton.setOnAction(e -> {
            String command = commandField.getText();
            String filePath = filePathField.getText();
            String keyText = keyField.getText();
            FileService fileService = new FileService();
            BruteForce bruteForce = new BruteForce();
            try {
                if ("ENCRYPT".equalsIgnoreCase(command) || "DECRYPT".equalsIgnoreCase(command)) {
                    int key = Integer.parseInt(keyText);
                    if ("ENCRYPT".equalsIgnoreCase(command)) {

                        fileService.encryptFile(filePath, key);
                        resultArea.setText("File encrypted.");
                    } else if ("DECRYPT".equalsIgnoreCase(command)) {
                        fileService.decryptFile(filePath, key);
                        resultArea.setText("File decrypted.");
                    }
                } else if ("BRUTE_FORCE".equalsIgnoreCase(command)) {
                    bruteForce.bruteForce(filePath);
                    resultArea.setText("Brute force complete.");
                } else {
                    resultArea.setText("Invalid command.");
                }
            } catch (IOException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid key: " + keyText);
            }
        });

        vbox.getChildren().addAll(
                commandLabel, commandField,
                filePathLabel, filePathField,
                keyLabel, keyField,
                executeButton, resultArea
        );

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

