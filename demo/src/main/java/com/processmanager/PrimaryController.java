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
import javafx.stage.Stage;

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
    private TableColumn<ProcessData, String> commandLineColumn;

    private ObservableList<ProcessData> processList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the TableView columns
        processIDColumn.setCellValueFactory(new PropertyValueFactory<>("processID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        commandLineColumn.setCellValueFactory(new PropertyValueFactory<>("commandLine"));

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
