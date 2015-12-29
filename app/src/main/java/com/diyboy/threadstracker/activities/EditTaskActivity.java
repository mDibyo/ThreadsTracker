package com.diyboy.threadstracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diyboy.threadstracker.R;

public class EditTaskActivity extends AppCompatActivity {
    public static final String LOG_TAG = "EditTaskActivity";

    public static final String ACTION_EDIT_OLD_TASK =
            PackageConstants.PACKAGE_NAME + ".EditTaskActivity.actions.editTask";
    public static final String ACTION_EDIT_CURRENT_TASK =
            PackageConstants.PACKAGE_NAME + ".EditTaskActivity.actions.addTask";

    public static final String EXTRA_TASK_TITLE =
            PackageConstants.PACKAGE_NAME + ".EditTaskActivity.extras.taskTitle";
    public static final String EXTRA_IS_EVENT =
            PackageConstants.PACKAGE_NAME + ".EditTaskActivity.extras.isEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TASK_TITLE)) {
            EditText editText = (EditText) findViewById(R.id.edit_task_title);
            editText.setText(intent.getStringExtra(EXTRA_TASK_TITLE));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void commitTask(View view) {
        finish();
    }
}
