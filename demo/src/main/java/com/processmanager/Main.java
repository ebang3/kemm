package com.processmanager;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OSProcess;
import oshi.util.FormatUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create a SystemInfo object to access OS and hardware data
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();

        // Get a list of all processes, sorted by CPU usage
        List<OSProcess> processes = os.getProcesses();

        // Display process information
        for (OSProcess process : processes) {
            String processName = process.getName();
            int processID = process.getProcessID();
            double cpuLoad = 100d * (process.getProcessCpuLoadCumulative());
            long memoryUsage = process.getResidentSetSize(); // In bytes
            long ioReadBytes = process.getBytesRead(); // I/O read in bytes
            long ioWriteBytes = process.getBytesWritten(); // I/O write in bytes

            // Classify the process as CPU-bound or I/O-bound
            String processType = classifyProcess(cpuLoad, ioReadBytes + ioWriteBytes);

            // Print process details
            System.out.printf("Process ID: %d, Name: %s%n", processID, processName);
            System.out.printf("CPU Load: %.2f%%, Memory Usage: %s%n", cpuLoad, FormatUtil.formatBytes(memoryUsage));
            System.out.printf("I/O: Read %s, Write %s%n", FormatUtil.formatBytes(ioReadBytes),
                    FormatUtil.formatBytes(ioWriteBytes));
            System.out.printf("Process Type: %s%n%n", processType);
        }
    }

    // Method to classify a process as CPU-bound or I/O-bound
    private static String classifyProcess(double cpuLoad, long ioTotalBytes) {
        if (cpuLoad > 75 && ioTotalBytes < 1048576) { // If CPU usage > 75% and I/O < 1 MB
            return "CPU-bound";
        } else if (ioTotalBytes > 1048576) { // If I/O > 1 MB
            return "I/O-bound";
        } else {
            return "Unknown";
        }
    }
}
