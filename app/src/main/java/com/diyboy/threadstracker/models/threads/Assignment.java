package com.diyboy.threadstracker.models.threads;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableDuration;

public class Assignment extends AbstractTask {
    public static final double DEFAULT_ASSIGNMENT_IMPORTANCE = 5;

    private ReadableDateTime deadline;
    private ReadableDuration requiredDuration;

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration, double importance) {
        super(title, importance);
        this.deadline = deadline;
        this.requiredDuration = requiredDuration;

    }

    public Assignment(String title, ReadableDateTime deadline, ReadableDuration requiredDuration) {
        this(title, deadline, requiredDuration, DEFAULT_ASSIGNMENT_IMPORTANCE);
    }

    public ReadableDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ReadableDateTime newDeadline) {
        deadline = newDeadline;
    }

    public ReadableDuration getRequiredDuration() {
        return requiredDuration;
    }

    public void setRequiredDuration(ReadableDuration newRequiredDuration) {
        this.requiredDuration = newRequiredDuration;
    }


}
