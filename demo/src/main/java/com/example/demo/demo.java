package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class demo {

    @FXML
    private VBox vbox;
    @FXML
    private Button sadd1;
    @FXML
    private Button tadd1;

    @FXML
    private Button tadd2;

    @FXML
    private Label task1;

    @FXML
    private Label task2;
    @FXML
    private Label task3;

    @FXML
    private Label time1;
    @FXML
    private Label time3;
    @FXML
    private Label time2;

    @FXML
    private TextField todo1;

    @FXML
    private TextField todo2;

    @FXML
    private TextField todo3;

    @FXML
    private TextField sc1;

    @FXML
    private TextField sc2;

    @FXML
    private TextField sc3;



    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_mainPage.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

    public void son1(ActionEvent event) {
        handleUpdate("Schedule", sc1);
        StudentLogin.student.time1 = sc1.getText();
        show();
    }

    public void taddon2(ActionEvent event) {
        handleUpdate("Todo2", todo2);
        StudentLogin.student.todo2 = todo2.getText();
        show();

    }

    public void taddon3(ActionEvent event) {
        handleUpdate("Todo3", todo3);
        StudentLogin.student.todo3 = todo3.getText();
        show();
    }

    public void son2(ActionEvent event) {
        handleUpdate("Schedule2", sc2);
        StudentLogin.student.time2 = sc2.getText();
        show();
    }

    void show(){
        task1.setText(StudentLogin.student.todo1);
        time1.setText(StudentLogin.student.time1);
        task2.setText(StudentLogin.student.todo2);
        time2.setText(StudentLogin.student.time2);
        task3.setText(StudentLogin.student.todo3);
        time3.setText(StudentLogin.student.time3);
    }

    public void son3(ActionEvent event) {
        handleUpdate("Schedule3", sc3);
        StudentLogin.student.time3 = sc3.getText();
        show();
    }

    private void handleUpdate(String fieldName, TextField textField) {
        if (!textField.getText().isEmpty()) {
            updateDatabase(fieldName, textField.getText());
        } else {
            System.out.println("TextField is empty. No update performed.");
        }
    }

    private void updateDatabase(String fieldName, String value) {
        databaseconnection_2 connectNow = new databaseconnection_2();
        try (Connection connectDB = connectNow.getConnection()) {
            String sql = "UPDATE logintable SET " + fieldName + " = ? WHERE username = ?";
            try (PreparedStatement stmt = connectDB.prepareStatement(sql)) {
                stmt.setString(1, value);
                stmt.setString(2, StudentLogin.student.user);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {

                    System.out.println(fieldName + " updated successfully for username " + StudentLogin.student.user);
                } else {
                    System.out.println("Username " + StudentLogin.student.user + " not found in the database.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void taddon1(ActionEvent event) {
        if(!todo1.getText().isEmpty() ){
            databaseconnection_2 connectNow = new databaseconnection_2();
            try (Connection connectDB = connectNow.getConnection()) {
                // Create the SQL UPDATE statement
                String sql = "UPDATE logintable SET Todo = ? WHERE username = ?";
                try (PreparedStatement stmt = connectDB.prepareStatement(sql)) {
                    // Set the parameters for the UPDATE statement
                    stmt.setString(1, todo1.getText());
                    stmt.setString(2, StudentLogin.student.user);

                    // Execute the UPDATE statement
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        StudentLogin.student.todo1 = todo1.getText();
                        System.out.println("todo updated successfully for username " + StudentLogin.student.user);
                    } else {
                        System.out.println("Username " + StudentLogin.student.user + " not found in the database.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        show();
    }


    private static class ClassSchedule {
        private String day;
        private String time;
        private String description;

        public ClassSchedule(String day, String time, String description) {
            this.day = day;
            this.time = time;
            this.description = description;
        }

        public String getDay() {
            return day;
        }

        public String getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return time + ": " + description;
        }
    }

    private ObservableList<ClassSchedule> todayClasses;
    private ObservableList<String> toDoItems;


    //@Override
    public void initialize() {
       // primaryStage.setTitle("Class Schedule");
        task1.setText(StudentLogin.student.todo1);
        time1.setText(StudentLogin.student.time1);
        task2.setText(StudentLogin.student.todo2);
        time2.setText(StudentLogin.student.time2);
        task3.setText(StudentLogin.student.todo3);
        time3.setText(StudentLogin.student.time3);
        // Initialize the schedule
        List<ClassSchedule> schedule = initializeSchedule();

        // Get today's day of the week and date
        LocalDate today = LocalDate.now();
         String dayOfWeek = today.getDayOfWeek().toString();
       // String dayOfWeek ="Tuesday";
        String formattedDate = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        // Filter today's classes
        todayClasses = FXCollections.observableArrayList();
        for (ClassSchedule cs : schedule) {
            if (cs.getDay().equalsIgnoreCase(dayOfWeek)) {
                todayClasses.add(cs);
            }
        }

        // UI Components

        Label dateLabel = new Label("Today is: " + dayOfWeek + ", " + formattedDate);
        ListView<ClassSchedule> listView = new ListView<>(todayClasses);
        listView.setCellFactory(param -> new ListCell<ClassSchedule>() {
            private int index = -1;

            @Override
            protected void updateItem(ClassSchedule item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getTime() + ": " + item.getDescription());
                    setTextFill(Color.BLACK); // Set text color
                    setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Set font
                    setStyle(index % 2 == 0 ? "-fx-background-color: skyblue; -fx-padding: 5px;" : "-fx-background-color: lavender; -fx-padding: 5px;"); // Set background color and padding
                    index++;
                }

            }
        });

        // To-Do List Components
        TextField toDoInput = new TextField();
        Button addButton = new Button("Add");
        toDoItems = FXCollections.observableArrayList();

        // Event handler for the Add button
        addButton.setOnAction(event -> {
            String newToDo = toDoInput.getText().trim();
            if (!newToDo.isEmpty()) {
                toDoItems.add(newToDo);
                toDoInput.clear();
            }
        });

        ListView<String> toDoListView = new ListView<>(toDoItems);


        vbox.getChildren().addAll(dateLabel, listView, toDoInput, addButton, toDoListView);

    }


    private List<ClassSchedule> initializeSchedule() {
        List<ClassSchedule> schedule = new ArrayList<>();

        // Add more classes as needed
        schedule.add(new ClassSchedule("Sunday", "11:10 AM", "Math-2105  MIB"));
        schedule.add(new ClassSchedule("Monday", "9:50 AM", "EEE Lab-2113"));
        schedule.add(new ClassSchedule("Tuesday", "9:50 AM", "EEE-2103 SA"));
        schedule.add(new ClassSchedule("Tuesday", "11:10 AM", "OOP-2102 RAR"));
        schedule.add(new ClassSchedule("Tuesday", "2:00 AM", "EEE-2113 RAR"));
        schedule.add(new ClassSchedule("Wednesday", "9:50 AM", "CSE-2101 MTA"));
        schedule.add(new ClassSchedule("Wednesday", "11:10 AM", "Math-2105 MIB"));
        schedule.add(new ClassSchedule("Wednesday", "2:00 AM", "GED-2104 SP"));

        return schedule;
    }


}
