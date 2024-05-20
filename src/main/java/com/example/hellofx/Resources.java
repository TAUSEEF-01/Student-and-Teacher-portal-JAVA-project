package com.example.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.File;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import java.io.IOException;


public class Resources implements Initializable {

    @FXML
    private Button oopbook;
    /*@FXML
    private Button eeebook;*/
    @FXML
    private TextField searching;

    @FXML
    private Label searchLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void eeeonAction(ActionEvent event) {
        // Specify the path to the PDF file
        String filePath = "/home/ubuntu/Papry Edu/EEE/Electronic_Devices_and_Circuit_Theory_11th_Boylestad_Solutions.pdf";
        File file = new File(filePath);

        // Open the file in a separate thread to prevent blocking the JavaFX application thread
        new Thread(() -> {
            try {
                // Check if Desktop is supported
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    // Check if the file exists
                    if (file.exists()) {
                        desktop.open(file);
                    } else {
                        System.out.println("File not found.");
                    }
                } else {
                    System.out.println("Desktop is not supported.");
                }
            } catch (IOException e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }
        }).start();
    }




    public void OOPonAction(ActionEvent actionEvent) {
        System.out.println("oop book opened");
        // Specify the path to the PDF file
        String filePath = "/home/ubuntu/Papry Edu/OOP/Java - The Complete Reference - 11 Edition.pdf";
        File file = new File(filePath);

        // Open the file in a separate thread to prevent blocking the JavaFX application thread
        new Thread(() -> {
            try {
                // Check if Desktop is supported
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    // Check if the file exists
                    if (file.exists()) {
                        desktop.open(file);
                    } else {
                        System.out.println("File not found.");
                    }
                } else {
                    System.out.println("Desktop is not supported.");
                }
            } catch (IOException e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }
        }).start();

    }

    public void searchOnAction(ActionEvent event) {

        if(!searching.getText().isEmpty() ) {

            String url = "https://www.google.com/search?q="+searching.getText()+"&sca_esv=e54de6d9ee195691&sca_upv=1&sxsrf=ADLYWILaHF0yEb0oK3UPQkeLL9d2phCNQg%3A1715364404250&ei=NGI-Zp3tDvrq1e8P9r6M4AM&ved=0ahUKEwid1-av1oOGAxV6dfUHHXYfAzwQ4dUDCBA&uact=5&oq=cat&gs_lp=Egxnd3Mtd2l6LXNlcnAiA2NhdDIKECMYgAQYJxiKBTINEC4YgAQYsQMYQxiKBTIKEAAYgAQYQxiKBTIKEAAYgAQYQxiKBTINEAAYgAQYsQMYQxiKBTINEAAYgAQYsQMYQxiKBTIKEC4YgAQYQxiKBTIKEC4YgAQYQxiKBTIKEAAYgAQYQxiKBTIKEAAYgAQYQxiKBTIcEC4YgAQYsQMYQxiKBRiXBRjcBBjeBBjfBNgBA0i7wQJQ7awCWKa7AnADeAGQAQGYAc0BoAHzBaoBBTAuNC4xuAEDyAEA-AEBmAIHoAK9BagCEsICChAAGLADGNYEGEfCAg0QABiABBiwAxhDGIoFwgIOEAAYsAMY5AIY1gTYAQHCAhMQLhiABBiwAxhDGMgDGIoF2AECwgIEECMYJ8ICBxAjGCcY6gLCAg0QLhjRAxjHARgnGOoCwgIWEC4YgAQYQxi0AhjIAxiKBRjqAtgBAsICGRAuGIAEGEMY1AIYtAIYyAMYigUY6gLYAQLCAg0QLhiABBhDGNQCGIoFwgIZEC4YgAQYQxiKBRiXBRjcBBjeBBjfBNgBA5gDDYgGAZAGE7oGBggBEAEYCboGBggCEAEYCLoGBggDEAEYFJIHBTMuMy4xoAfplAE&sclient=gws-wiz-serp";

            // Open the file in a separate thread to prevent blocking the JavaFX application thread
            new Thread(() -> {
                try {
                    // Check if Desktop is supported
                    if (Desktop.isDesktopSupported()) {
                        // Get the Desktop instance
                        Desktop desktop = Desktop.getDesktop();
                        // Open the default browser and navigate to the specified URL
                        desktop.browse(new URI(url));
                    } else {
                        System.out.println("Desktop is not supported.");
                    }
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace(); // Print the stack trace for debugging
                }
            }).start();
        } else {
            searchLabel.setText("Please Write Something What You Want to Know");
        }
    }

    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }
}