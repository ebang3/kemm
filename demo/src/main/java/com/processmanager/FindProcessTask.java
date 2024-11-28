package com.processmanager;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class FindProcessTask extends Task<ObservableList<ProcessData>> {

  @Override
  protected ObservableList<ProcessData> call() throws Exception {
    // Get the system information and processes in the background
    SystemInfo sysInfo = new SystemInfo();
    OperatingSystem os = sysInfo.getOperatingSystem();
    List<OSProcess> processes = os.getProcesses();

    ObservableList<ProcessData> processList = FXCollections.observableArrayList();

    for (OSProcess osProcess : processes) {
      // Create ProcessData for each process
      ProcessData process = new ProcessData(
          osProcess.getProcessID(),
          osProcess.getName(),
          osProcess.getUser(),
          osProcess.getCommandLine(),
          osProcess.getProcessCpuLoadCumulative() * 100,
          osProcess.getResidentSetSize(),
          osProcess.getBytesRead() + osProcess.getBytesWritten()
      );
      processList.add(process);
    }
    return processList;
  }
}
