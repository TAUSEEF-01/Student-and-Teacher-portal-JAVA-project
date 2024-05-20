package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



class Courses{
    double credit;
    double marks;
    Courses(double credit){
        this.credit=credit;
    }
    void marks(double m){
        this.marks=m;
    }
    void marks(double m, int i){
        this.marks=m*i;
    }
    String grade(){
    double marks= this.marks;
        if (marks >= 80) {
            return "A+";
        } else if (marks >= 75) {
            return "A";
        } else if (marks >= 70) {
            return "A-";
        }
        else if (marks >= 65) {
            return "B+";
        }
        else if (marks >= 60) {
            return "B";
        }
        else if (marks >= 55) {
            return "B-";
        }
        else if (marks >= 50) {
            return "C+";
        }
        else if (marks >= 45) {
            return "C";
        }
        else if (marks >= 40) {
            return "C-";
        }
        else if (marks >= 33) {
            return "D";
        }
        else {
            return "F";
        }
    }

    double gpa(){
        double marks= this.marks;
        if (marks >= 80) {
            return 4.0;
        } else if (marks >= 75) {
            return 3.75;
        } else if (marks >= 70) {
            return 3.5;
        }
        else if (marks >= 65) {
            return 3.25;
        }
        else if (marks >= 60) {
            return 3;
        }
        else if (marks >= 55) {
            return 2.75;
        }
        else if (marks >= 50) {
            return 2.5;
        }
        else if (marks >= 45) {
            return 2.25;
        }
        else if (marks >= 40) {
            return 2;
        }
        else if (marks >= 33) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

public class Cgpa{



    Courses OOP = new Courses(3);
    Courses DSA= new Courses(3);
    Courses EEE= new Courses(3);
    Courses GED= new Courses(2);
    Courses Math= new Courses(3);
    Courses DSALab= new Courses(1.5);
    Courses OOPLab= new Courses(1.5);
    Courses EEELab= new Courses(0.75);

    @FXML
    private TextField oopMarks;
    @FXML
    private TextField dsamarks;
    @FXML
    private TextField eeemarks;
    @FXML
    private TextField mathmarks;
    @FXML
    private TextField ooplabmarks;
    @FXML
    private TextField dsalabmarks;
    @FXML
    private TextField eeelabmarks;
    @FXML
    private TextField gedmarks;
    @FXML
    private Label oopg;
    @FXML
    private Label dsag;
    @FXML
    private Label eeeg;
    @FXML
    private Label mathg;
    @FXML
    private Label gedg;
    @FXML
    private Label ooplabg;
    @FXML
    private Label dsalabg;
    @FXML
    private Label eeelabg;
    @FXML
    private Label cgpa;

    @FXML
    private Button calculateButton;

    @FXML
    public void initialize() {
        // Initialize method called after FXML file is loaded
        // You can perform any initialization tasks here
    }

    @FXML
    public void calccgpa(ActionEvent event) {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        String PASSWORD = "password";
        String username = StudentLogin.student.user; // Replace with actual username

        OOP.marks( Double.parseDouble(oopMarks.getText()));
        DSA.marks( Double.parseDouble(dsamarks.getText()));
        EEE.marks(Double.parseDouble(eeemarks.getText()));
        Math.marks(Double.parseDouble(mathmarks.getText()));
        OOPLab.marks(Double.parseDouble(ooplabmarks.getText()),2);
        DSALab.marks(Double.parseDouble(dsalabmarks.getText()),2);
        EEELab.marks (Double.parseDouble(eeelabmarks.getText()),2);
        GED.marks = Double.parseDouble(gedmarks.getText());
        double totalCredits = 17.75; // 7 courses


        // Setting grades to respective labels
        oopg.setText(OOP.grade());
        dsag.setText(DSA.grade());
        eeeg.setText(EEELab.grade());
        mathg.setText(Math.grade());
        ooplabg.setText(OOPLab.grade());
        dsalabg.setText(DSALab.grade());
        eeelabg.setText(EEELab.grade());
        gedg.setText(GED.grade());

        // Calculating total grade points
        double totalGradePoints = OOP.gpa() * OOP.credit + DSA.gpa() * DSA.credit + EEE.gpa() * 3 + Math.gpa() * 3 + DSALab.gpa() * 1.5 + EEELab.gpa() * 0.75 + GED.gpa() * 2 + OOPLab.gpa() * 1.5;


        // Calculating CGPA
        double cgpaValue = totalGradePoints / totalCredits;

        // Displaying CGPA
        cgpa.setText(String.format("%.2f", cgpaValue));
        updateCGPA(username, cgpaValue);
    }

        private void updateCGPA(String username, double cgpa) {
            databaseconnection_2 connectNow = new databaseconnection_2();
            try (Connection connectDB = connectNow.getConnection()) {
                // Create the SQL UPDATE statement
                String sql = "UPDATE logintable SET cgpa = ? WHERE username = ?";
                try (PreparedStatement stmt = connectDB.prepareStatement(sql)) {
                    // Set the parameters for the UPDATE statement
                    stmt.setDouble(1, cgpa);
                    stmt.setString(2, username);

                    // Execute the UPDATE statement
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("CGPA updated successfully for username " + username);
                    } else {
                        System.out.println("Username " + username + " not found in the database.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
