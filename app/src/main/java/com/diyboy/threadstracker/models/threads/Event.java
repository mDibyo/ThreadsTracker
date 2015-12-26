package com.diyboy.threadstracker.models.threads;


public class Event extends AbstractTask {
    public static final double DEFAULT_EVENT_IMPORTANCE = 11;

    public Event(String title, double importance) {
        super(title, importance);
    }

    public Event(String title) {
        this(title, DEFAULT_EVENT_IMPORTANCE);
    }
}
