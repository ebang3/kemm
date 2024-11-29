package com.processmanager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    private FindProcessTask findProcessTask;

    @FXML
    private TableView<ProcessData> processTableView;

    @FXML
    private TableColumn<ProcessData, Long> processIDColumn;

    @FXML
    private TableColumn<ProcessData, String> nameColumn;

    @FXML
    private TableColumn<ProcessData, String> userColumn;

    @FXML
    private TableColumn<ProcessData, Double> cpuUsageColumn; // New column for CPU usage

    @FXML
    private TableColumn<ProcessData, Double> memoryUsageColumn; // New column for CPU usage

    @FXML
    private TableColumn<ProcessData, Double> ioUsageColumn; // New column for I/O usage

    private ObservableList<ProcessData> processList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the TableView columns
        processIDColumn.setCellValueFactory(new PropertyValueFactory<>("processID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpuUsageColumn.setCellValueFactory(new PropertyValueFactory<>("cpuUsage")); // Bind CPU usage column
        memoryUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memoryUsage")); // Bind CPU usage column
        ioUsageColumn.setCellValueFactory(new PropertyValueFactory<>("ioUsage")); // Bind I/O usage column

        // Set the cell factory for cpuUsageColumn to format the cpu usage as a
        // percentage with 1 decimal place
        cpuUsageColumn.setCellFactory(col -> {
            return new TextFieldTableCell<ProcessData, Double>() {
                @Override
                public void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Format the CPU usage as a percentage with 1 decimal place
                        setText(String.format("%.1f", item) + "%");
                    }
                }
            };
        });

        memoryUsageColumn.setCellFactory(col -> {
            return new TextFieldTableCell<ProcessData, Double>() {
                @Override
                public void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Format the CPU usage as a percentage with 1 decimal place
                        setText(String.format("%.1f", item) + " MB");
                    }
                }
            };
        });

        ioUsageColumn.setCellFactory(col -> {
            return new TextFieldTableCell<ProcessData, Double>() {
                @Override
                public void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Format the CPU usage as a percentage with 1 decimal place
                        setText(String.format("%.1f", item) + " MB/s");
                    }
                }
            };
        });

        // Bind the observable list to the TableView
        processTableView.setItems(processList);

    }

    public void invokeFindProcessTask() {
        if (findProcessTask != null && findProcessTask.isRunning()) {
            findProcessTask.cancel();
        }

        // Create a new task to fetch process data
        findProcessTask = new FindProcessTask();

        // Bind the task's output to the TableView
        findProcessTask.setOnSucceeded(event -> {
            processList.setAll(findProcessTask.getValue());
        });

        findProcessTask.setOnFailed(event -> {
            Throwable error = findProcessTask.getException();
            error.printStackTrace();
        });

        // Run the task in a background thread
        new Thread(findProcessTask).start();
    }

    private void endSelectedTask() {
        ProcessData selectedProcess = processTableView.getSelectionModel().getSelectedItem();
        if (selectedProcess != null) {
            try {
                int processID = (int) selectedProcess.getProcessID();
                Runtime.getRuntime().exec("taskkill /PID " + processID + " /F");
                invokeFindProcessTask(); // Refresh process list
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Failed to terminate the process.");
                    alert.showAndWait();
                });
            }
        }
    }
}
