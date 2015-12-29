package com.diyboy.threadstracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.diyboy.threadstracker.R;

public class CreateNewTaskActivity extends AppCompatActivity {
    public static final String CREATE_NEW_TASK_ACTIVITY_LOG_TAG = "CreateNewTaskActivity";

    public static final String EXTRA_TASK_TITLE =
            "com.diyboy.threadstracker.activities.createnewtaskactivity.tasktitleaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TASK_TITLE)) {
            EditText editText = (EditText) findViewById(R.id.edit_task_name);
            editText.setText(intent.getStringExtra(EXTRA_TASK_TITLE));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void commitTask(View view) {
        Log.i(CREATE_NEW_TASK_ACTIVITY_LOG_TAG, "commitTask called");
        finish();
    }
}
