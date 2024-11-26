package com.processmanager;

import java.util.List;

import javafx.concurrent.Task;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class FindProcessTask extends Task<ProcessData> {

  private ProcessData process;

  @Override
  protected ProcessData call() throws Exception {
    // Get the system information and processes in the background
    SystemInfo sysInfo = new SystemInfo();
    OperatingSystem os = sysInfo.getOperatingSystem();
    List<OSProcess> processes = os.getProcesses();

    OSProcess osProcess = processes.get(0);
    process = new ProcessData(
        osProcess.getProcessID(),
        osProcess.getName(),
        osProcess.getUser(),
        osProcess.getCommandLine());
    return process;

  }

}
