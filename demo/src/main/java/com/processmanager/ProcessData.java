package com.processmanager;

import javafx.beans.property.*;

public class ProcessData {
  private final LongProperty processID;
  private final StringProperty name;
  private final DoubleProperty cpuUsage;
  private final DoubleProperty memoryUsage;
  private final DoubleProperty ioUsage;
  private final StringProperty cpuOrIO;

  public ProcessData(long processID, String name, double cpuUsage, double memoryUsage, double ioUsage,
      String cpuOrIO) {
    this.processID = new SimpleLongProperty(processID);
    this.name = new SimpleStringProperty(name);
    this.cpuUsage = new SimpleDoubleProperty(cpuUsage);
    this.memoryUsage = new SimpleDoubleProperty(memoryUsage);
    this.ioUsage = new SimpleDoubleProperty(ioUsage);
    this.cpuOrIO = new SimpleStringProperty(cpuOrIO);
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

  public double getIoUsage() {
    return ioUsage.get();
  }

  public DoubleProperty ioUsageProperty() {
    return ioUsage;
  }

  public String getCpuOrIO() {
    return cpuOrIO.get();
  }

  public StringProperty cpuOrIOProperty() {
    return cpuOrIO;
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
