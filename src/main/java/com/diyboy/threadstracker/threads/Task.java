package main.java.com.diyboy.threadstracker.threads;

import org.joda.time.DateTime;

public class Task extends Event implements Activity {
  private DateTime deadline;

  public Task(String title, DateTime deadline, double importance) {
    super(title, importance);
    this.deadline = deadline;
  }

  public Task(String title, DateTime deadline) {
    super(title);
    this.deadline = deadline;
  }

  public DateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(DateTime newDeadline) {
    deadline = newDeadline;
  }


}
