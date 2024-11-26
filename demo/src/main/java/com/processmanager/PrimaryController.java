package com.processmanager;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; // Make sure to import Initializable
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable { // Implement Initializable

    private FindProcessTask findProcessTask;

    @FXML
    private TableView<ProcessData> processTableView; // TableView to display process data

    @FXML
    private TableColumn<ProcessData, Long> processIDColumn; // Column for Process ID
    @FXML
    private TableColumn<ProcessData, String> nameColumn; // Column for Process Name
    @FXML
    private TableColumn<ProcessData, String> userColumn; // Column for User
    @FXML
    private TableColumn<ProcessData, String> commandLineColumn; // Column for Command Line

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) { // Override initialize() method

        // Set up the columns in the TableView
        // processIDColumn.setCellValueFactory(cellData ->
        // cellData.getValue().processIDProperty().asObject());
        // nameColumn.setCellValueFactory(cellData ->
        // cellData.getValue().nameProperty());
        // userColumn.setCellValueFactory(cellData ->
        // cellData.getValue().userProperty());
        // commandLineColumn.setCellValueFactory(cellData ->
        // cellData.getValue().commandLineProperty());
    }

    public void invokeFindProcessTask() {
        if (findProcessTask != null && findProcessTask.isRunning()) {
            findProcessTask.cancel();
        }

        findProcessTask = new FindProcessTask();
    }
}
