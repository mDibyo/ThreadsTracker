package com.diyboy.threadstracker.controllers;


import android.content.Context;
import android.content.Intent;

import com.diyboy.threadstracker.activities.EditTaskActivity;
import com.diyboy.threadstracker.models.ThreadsTrackerState;
import com.diyboy.threadstracker.models.threads.Event;
import com.diyboy.threadstracker.models.threads.Task;

public class TasksController {
    public static final String LOG_TAG = "TaskController";

    private Context mContext;
    private ThreadsTrackerState mThreadsTrackerState;

    public TasksController(Context context) {
        mContext = context;
        mThreadsTrackerState = ThreadsTrackerState.getInstance(context);
    }

    public void startEditCurrentTaskActivity() {
        startEditTaskActivityWithAction(
                mThreadsTrackerState.getCurrentTask(), EditTaskActivity.ACTION_EDIT_CURRENT_TASK);
    }

    public void startEditTaskActivity(Task task) {
        startEditTaskActivityWithAction(task, EditTaskActivity.ACTION_EDIT_OLD_TASK);
    }

    public void startEditTaskActivityWithAction(Task task, String action) {
        Intent intent = new Intent(mContext, EditTaskActivity.class);
        addTaskToIntent(intent, task);
        intent.setAction(action);
        mContext.startActivity(intent);
    }

    private void addTaskToIntent(Intent intent, Task task) {
        intent.putExtra(EditTaskActivity.EXTRA_TASK_TITLE, task.getTitle());
        if (task instanceof Event) {
            intent.putExtra(EditTaskActivity.EXTRA_IS_EVENT, true);
        } else {
            intent.putExtra(EditTaskActivity.EXTRA_IS_EVENT, false);
        }
    }
}
