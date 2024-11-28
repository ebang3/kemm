package com.processmanager;

import javafx.beans.property.*;

public class ProcessData {
  private final LongProperty processID;
  private final StringProperty name;
  private final StringProperty user;
  private final StringProperty commandLine;
  private final DoubleProperty cpuUsage;
  private final LongProperty memoryUsage;
  private final LongProperty diskUsage;

  public ProcessData(long processID, String name, String user, String commandLine, double cpuUsage, long memoryUsage, long diskUsage) {
    this.processID = new SimpleLongProperty(processID);
    this.name = new SimpleStringProperty(name);
    this.user = new SimpleStringProperty(user);
    this.commandLine = new SimpleStringProperty(commandLine);
    this.cpuUsage = new SimpleDoubleProperty(cpuUsage);
    this.memoryUsage = new SimpleLongProperty(memoryUsage);
    this.diskUsage = new SimpleLongProperty(diskUsage);
  }

  public long getProcessID() {
    return processID.get();
  }

  public LongProperty processIDProperty() {
    return processID;
  }

  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public String getUser() {
    return user.get();
  }

  public StringProperty userProperty() {
    return user;
  }

  public String getCommandLine() {
    return commandLine.get();
  }

  public StringProperty commandLineProperty() {
    return commandLine;
  }
  public double getCpuUsage() {
    return cpuUsage.get();
  }

  public DoubleProperty cpuUsageProperty() {
    return cpuUsage;
  }

  public long getMemoryUsage() {
    return memoryUsage.get();
  }

  public LongProperty memoryUsageProperty() {
    return memoryUsage;
  }

  public long getDiskUsage() {
    return diskUsage.get();
  }

  public LongProperty diskUsageProperty() {
    return diskUsage;
  }
}
