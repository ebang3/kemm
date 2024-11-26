package com.processmanager;

import javafx.beans.property.*;

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
}
