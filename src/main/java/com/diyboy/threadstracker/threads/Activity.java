package main.java.com.diyboy.threadstracker.threads;


import java.util.UUID;

public interface Activity {
  UUID getUuid();

  String getTitle();

  void setTitle(String title);

  double getImportance();

  void setImportance(double importance);

  boolean isDone();

  void setDone(boolean done);

}
