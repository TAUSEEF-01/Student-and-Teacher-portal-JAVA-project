package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private VBox vbox;

    @FXML
    private Label welcomeText;
    @FXML
    private Label welcomeLabel;


    @FXML
    protected void ChatbotButtonOnAction(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();

        Stage current_stage = (Stage) welcomeLabel.getScene().getWindow();
        current_stage.close();
    }

    @FXML
    protected void messageButtonOnAction(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("message.fxml"));
//            root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 900, 580));
        stage.setResizable(false);
        stage.show();

        Stage current_stage = (Stage) welcomeLabel.getScene().getWindow();
        current_stage.close();
    }


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
        welcomeLabel.setText("Name: " + StudentLogin.student.fname + " " + StudentLogin.student.lname+"\n"+"Registration No: "+ StudentLogin.student.reg+ "\n" +"Roll: "+ StudentLogin.student.roll);
        System.out.println("Student's first name: " + StudentLogin.student.fname);
        System.out.println("Student's last name: " + StudentLogin.student.lname);
        System.out.println("Student's registration number: " + StudentLogin.student.reg);
    }


    public void rotOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("routine.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }


    public void assignmentButtonOnAction(ActionEvent event) throws IOException { // assignments section
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Assignments.fxml"));
        Parent root = fxmlLoader.load();

        AssignmentController assignmentController = fxmlLoader.getController();
        assignmentController.fillList(Central_Course_MS.assignments);

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



    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentLogin.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void switchToTeacher(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/TeacherDashboard.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }
}
