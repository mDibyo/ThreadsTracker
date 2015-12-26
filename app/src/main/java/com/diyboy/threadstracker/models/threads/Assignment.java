package com.diyboy.threadstracker.models.threads;

import org.joda.time.DateTime;

public class Assignment extends AbstractTask {
    public static final double DEFAULT_ASSIGNMENT_IMPORTANCE = 5;

    private DateTime deadline;

    public Assignment(String title, DateTime deadline, double importance) {
        super(title, importance);
        this.deadline = deadline;
    }

    public Assignment(String title, DateTime deadline) {
        super(title, DEFAULT_ASSIGNMENT_IMPORTANCE);
        this.deadline = deadline;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime newDeadline) {
        deadline = newDeadline;
    }


}
