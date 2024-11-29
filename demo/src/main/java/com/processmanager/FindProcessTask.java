package com.processmanager;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
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
      long processID = osProcess.getProcessID();
      String name = osProcess.getName();
      String cpuOrIO = "";

      double cpuUsage = osProcess.getProcessCpuLoadBetweenTicks(osProcess) * 100;
      // Skip processes with no CPU usage
      if (cpuUsage == 0 || cpuUsage > 100) {
        continue; // Skip to the next process
      }
      long usedMemory = osProcess.getResidentSetSize();
      double memoryUsage = ((double) usedMemory) / (1024 * 1024);
      double ioByteUsage = osProcess.getBytesRead() + osProcess.getBytesWritten();
      double ioUsage = (ioByteUsage) / (1024.0 * 1024.0);

      if (cpuUsage > 50 && ioByteUsage < (1024 * 1024)) {
        cpuOrIO = "CPU";
      } else if (cpuUsage < 20 && ioByteUsage > (10 * 1024 * 1024)) {
        cpuOrIO = "I/O";
      } else {
        continue;
      }
      // Create ProcessData for each process
      ProcessData process = new ProcessData(
          processID, name, cpuUsage, memoryUsage, ioUsage, cpuOrIO);
      processList.add(process);
    }
    return processList;
  }
}
