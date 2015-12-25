package main.java.com.diyboy.threadstracker.threads;

import org.joda.time.DateTime;

public class Assignment extends Event implements Task {
  private DateTime deadline;

  public Assignment(String title, DateTime deadline, double importance) {
    super(title, importance);
    this.deadline = deadline;
  }

  public Assignment(String title, DateTime deadline) {
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
