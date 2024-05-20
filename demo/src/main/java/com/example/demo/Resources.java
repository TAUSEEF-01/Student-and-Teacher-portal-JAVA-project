package com.example.demo;

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
import javafx.scene.control.TextField;

import java.awt.Desktop;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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
    private TextField bookpath;

    @FXML
    private Label searchLabel;

    public void mathOnAction(ActionEvent event) {
        openbook math= new openbook();
        math.boolocation= "D:\\Downloads\\DUCSE Documents\\2nd year\\EEE\\Books\\Chapter 1-2 11th Edition Solutions.pdf";
        math.open();
    }

    public void cseOnAction(ActionEvent event) {
        openbook dsa= new openbook();
        dsa.boolocation= "D:\\Downloads\\DUCSE Documents\\2nd year\\EEE\\Books\\Chapter 1-2 11th Edition Solutions.pdf";
        dsa.open();
    }

    class openbook{
        String boolocation;
        void open(){
            String filePath = this.boolocation;
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void eeeonAction(ActionEvent event) {
        openbook eee= new openbook();
        eee.boolocation="D:\\Downloads\\DUCSE Documents\\2nd year\\EEE\\Books\\Chapter 1-2 11th Edition Solutions.pdf";
        eee.open();

    }




    public void OOPonAction(ActionEvent actionEvent) {
        openbook eee= new openbook();
        eee.boolocation="D:\\Downloads\\DUCSE Documents\\2nd year\\EEE\\Books\\Chapter 1-2 11th Edition Solutions.pdf";
        eee.open();

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_mainPage.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void mybookOnAction(ActionEvent event) {

        System.out.println("my book opened");
        // Specify the path to the PDF file
        String filePath = StudentLogin.student.mybook;
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

    public void addOnAction(ActionEvent event) {
        if(!bookpath.getText().isEmpty() ){
            databaseconnection_2 connectNow = new databaseconnection_2();
            try (Connection connectDB = connectNow.getConnection()) {
                // Create the SQL UPDATE statement
                String sql = "UPDATE logintable SET Mybook = ? WHERE username = ?";
                try (PreparedStatement stmt = connectDB.prepareStatement(sql)) {
                    // Set the parameters for the UPDATE statement
                    stmt.setString(1, bookpath.getText());
                    stmt.setString(2, StudentLogin.student.user);

                    // Execute the UPDATE statement
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        StudentLogin.student.mybook = bookpath.getText();
                        System.out.println("book updated successfully for username " + StudentLogin.student.user);
                    } else {
                        System.out.println("Username " + StudentLogin.student.user + " not found in the database.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}