package com.example.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HelloController {

    @FXML
    private VBox vbox;

    @FXML
    private Label welcomeText;
    @FXML
    private Label welcomeLabel;


    /*@FXML
    public void initialize() {
        for (int i = 0; i < 5; i++) {
            Button button = new Button("Hello!");
            button.setOnAction(e -> onHelloButtonClick());
            vbox.getChildren().add(button);
        }
    }*/


    public  void initialize(){
       setStudentData();
    }
    public void setStudentData() {
        // Use the student data as needed, for example:
        welcomeLabel.setText("Welcome, " + Login.student.fname + " " + Login.student.lname+"\n"+" Registration No: "+Login.student.reg+ " Roll: "+ Login.student.roll);
        System.out.println("Student's first name: " + Login.student.fname);
        System.out.println("Student's last name: " + Login.student.lname);
        System.out.println("Student's registration number: " + Login.student.reg);
    }


    public void rotOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("routine.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void resOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void CCalcOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cgpa.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void RLOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rank.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }
}
