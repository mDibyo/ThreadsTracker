package com.diyboy.threadstracker.controllers;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.diyboy.threadstracker.activities.CreateNewTaskActivity;
import com.diyboy.threadstracker.models.ThreadsTrackerState;
import com.diyboy.threadstracker.models.threads.Task;

public class TasksController {
    private Context mContext;
    private ThreadsTrackerState mThreadsTrackerState;

    public TasksController(Context context) {
        mContext = context;
        mThreadsTrackerState = ThreadsTrackerState.getInstance(context);
    }

    public void addTaskActivity() {
        Intent intent = new Intent(mContext, CreateNewTaskActivity.class);
        mContext.startActivity(intent);
    }

    public void editTaskActivity(Task task) {
        Intent intent = new Intent(mContext, CreateNewTaskActivity.class);
        intent.setAction()
        intent.putExtra(CreateNewTaskActivity.EXTRA_TASK_TITLE, task.getTitle());
        mContext.startActivity(intent);
    }
}
