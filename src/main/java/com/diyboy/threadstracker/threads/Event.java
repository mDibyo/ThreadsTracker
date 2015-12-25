package main.java.com.diyboy.threadstracker.threads;


public class Event extends AbstractTask {
  public static final double DEFAULT_EVENT_IMPORTANCE = 11;

  public Event(String title) {
    super(title, DEFAULT_EVENT_IMPORTANCE);
  }
}
