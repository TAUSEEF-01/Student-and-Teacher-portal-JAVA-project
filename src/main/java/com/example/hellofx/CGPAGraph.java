package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CGPAGraph {

    @FXML
    private VBox vBox;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "baby1_2panda";

    @FXML
    public void initialize() {
        try {
            // Create the x and y axes
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            // Create the bar chart
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("CGPA vs Roll");
            xAxis.setLabel("Roll");
            yAxis.setLabel("CGPA");

            // Fetch data from the database
            ObservableList<XYChart.Data<String, Number>> chartData = fetchDataFromDatabase();

            // Add data to the chart
            XYChart.Series<String, Number> series = new XYChart.Series<>(chartData);
            barChart.getData().add(series);

            // Add the chart to the VBox
            vBox.getChildren().add(barChart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<XYChart.Data<String, Number>> fetchDataFromDatabase() throws SQLException {
        ObservableList<XYChart.Data<String, Number>> chartData = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Roll, CGPA FROM logintable ORDER BY Roll ASC")) {

            while (rs.next()) {
                String roll = rs.getString("Roll");
                double cgpa = rs.getDouble("CGPA");
                chartData.add(new XYChart.Data<>(roll, cgpa));
            }
        }

        return chartData;
    }


    public void homeOnAction(ActionEvent event) throws IOException {
        // Handle the home button action
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }
}
