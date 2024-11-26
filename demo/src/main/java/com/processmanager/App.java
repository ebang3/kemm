package com.processmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OSProcess;

import java.io.IOException;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // SystemInfo sysInfo = new SystemInfo();
        // OperatingSystem os = sysInfo.getOperatingSystem();

        // List<OSProcess> processes = os.getProcesses();

        // for (OSProcess process : processes) {
        // System.out.println("Process ID: " + process.getProcessID());
        // System.out.println("Process Name: " + process.getName());
        // System.out.println("User: " + process.getUser());
        // System.out.println("Command Line: " + process.getCommandLine());
        // System.out.println();
        // }

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}