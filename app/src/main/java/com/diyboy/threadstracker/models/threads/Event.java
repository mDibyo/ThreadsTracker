package com.diyboy.threadstracker.models.threads;


import org.joda.time.ReadableInterval;

public class Event extends AbstractTask {
    public static final double DEFAULT_EVENT_IMPORTANCE = 11;

    private ReadableInterval mInterval;

    public Event(String title, ReadableInterval interval, double importance) {
        super(title, importance);
        mInterval = interval;
    }

    public Event(String title, ReadableInterval interval) {
        this(title, interval, DEFAULT_EVENT_IMPORTANCE);
    }

    public ReadableInterval getInterval() {
        return mInterval;
    }

    public void setInterval(ReadableInterval interval) {
        mInterval = interval;
    }
}
