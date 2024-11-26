package com.processmanager;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProcessData {

  private final LongProperty processID;
  private final StringProperty name;
  private final StringProperty user;
  private final StringProperty commandLine;

  public ProcessData(long processID, String name, String user, String commandLine) {
    this.processID = new SimpleLongProperty(processID);
    this.name = new SimpleStringProperty(name);
    this.user = new SimpleStringProperty(user);
    this.commandLine = new SimpleStringProperty(commandLine);
  }

  // Getter methods for the properties
  public LongProperty processIDProperty() {
    return processID;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty userProperty() {
    return user;
  }

  public StringProperty commandLineProperty() {
    return commandLine;
  }

  // Regular getter methods
  public long getProcessID() {
    return processID.get();
  }

  public String getName() {
    return name.get();
  }

  public String getUser() {
    return user.get();
  }

  public String getCommandLine() {
    return commandLine.get();
  }
}
