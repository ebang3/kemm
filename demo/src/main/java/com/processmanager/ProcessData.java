package com.processmanager;

import javafx.beans.property.*;

public class ProcessData {
  private final LongProperty processID;
  private final StringProperty name;
  private final StringProperty user;
  private final DoubleProperty cpuUsage;
  private final DoubleProperty memoryUsage;
  private final DoubleProperty ioUsage;

  public ProcessData(long processID, String name, String user, double cpuUsage, double memoryUsage, double ioUsage) {
    this.processID = new SimpleLongProperty(processID);
    this.name = new SimpleStringProperty(name);
    this.user = new SimpleStringProperty(user);
    this.cpuUsage = new SimpleDoubleProperty(cpuUsage);
    this.memoryUsage = new SimpleDoubleProperty(memoryUsage);
    this.ioUsage = new SimpleDoubleProperty(ioUsage);
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

  public double getCpuUsage() {
    return cpuUsage.get();
  }

  public DoubleProperty cpuUsageProperty() {
    return cpuUsage;
  }

  public double getMemoryUsage() {
    return memoryUsage.get();
  }

  public DoubleProperty MemoryUsageProperty() {
    return memoryUsage;
  }

  public double getIoUsage() {
    return ioUsage.get();
  }

  public DoubleProperty ioUsageProperty() {
    return ioUsage;
  }
}
