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
    GlobalMemory globalMemory = sysInfo.getHardware().getMemory();
    long totalMemory = globalMemory.getTotal();

    for (OSProcess osProcess : processes) {
      long processID = osProcess.getProcessID();
      String name = osProcess.getName();
      String user = osProcess.getUser();

      double cpuUsage = osProcess.getProcessCpuLoadBetweenTicks(osProcess) * 100;
      // Skip processes with no CPU usage
      if (cpuUsage < 1 || cpuUsage > 100) {
        continue; // Skip to the next process
      }
      long usedMemory = osProcess.getResidentSetSize();
      double memoryUsage = (double) ((usedMemory * 100) / totalMemory);
      double ioUsage = (osProcess.getBytesRead() + osProcess.getBytesWritten()) / 1024.0;
      // Create ProcessData for each process
      ProcessData process = new ProcessData(
          processID, name, user, cpuUsage, memoryUsage, ioUsage);
      processList.add(process);
    }
    return processList;
  }
}
