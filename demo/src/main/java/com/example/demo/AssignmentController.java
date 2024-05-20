package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignmentController implements Initializable {
    @FXML
    private ListView<String> assignmentListView;
    private String selectedCourse;
    private String studentName;

    @FXML
    TextField studentNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting listener from assignment listView
        assignmentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedCourse = assignmentListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedCourse); // checking
            }
        });
    }


    @FXML
    public void fillList(String assignment){
//        System.out.println("Asssssssssss " + assignment); // checking
        assignmentListView.getItems().add(assignment);

        System.out.println("Assignment List - " + assignmentListView.getItems()); // checking
    }

    @FXML
    public void fillList(List<String> assignment){
//        System.out.println("Asssssssssss " + assignment); // checking
        assignmentListView.getItems().addAll(assignment);

        System.out.println("Assignment List - " + assignmentListView.getItems()); // checking
    }

    public void submit(ActionEvent event){
        studentName = studentNameTextField.getText();
        if(studentName != null){
            Central_Course_MS.submittedAssignments.add(selectedCourse + " " + studentName);
            assignmentListView.getItems().remove(selectedCourse);
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


}
