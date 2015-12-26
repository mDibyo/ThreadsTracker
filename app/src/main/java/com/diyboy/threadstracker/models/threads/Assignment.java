package com.diyboy.threadstracker.models.threads;

import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

public class Assignment extends AbstractTask {
    public static final double DEFAULT_ASSIGNMENT_IMPORTANCE = 5;

    private ReadableDateTime mDeadline;
    private ReadableDuration mRequiredDuration;

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration, double importance) {
        super(title, importance);
        mDeadline = deadline;
        mRequiredDuration = requiredDuration;

    }

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration) {
        this(title, deadline, requiredDuration, DEFAULT_ASSIGNMENT_IMPORTANCE);
    }

    public ReadableDateTime getDeadline() {
        return mDeadline;
    }

    public void setDeadline(ReadableDateTime deadline) {
        mDeadline = deadline;
    }

    public ReadableDuration getRequiredDuration() {
        return mRequiredDuration;
    }

    public void setRequiredDuration(ReadableDuration requiredDuration) {
        mRequiredDuration = requiredDuration;
    }


}
