package com.example.to_do_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TaskInputFragment.OnTaskSavedListener {

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<String> taskList = new ArrayList<>();
    private FloatingActionButton addTaskFAB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity", "onCreate function");
        // Initialize RecyclerView
        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapter and set it to RecyclerView
        taskAdapter = new TaskAdapter(taskList);
        taskRecyclerView.setAdapter(taskAdapter);

        // Initialize FAB and set onClick listener
        addTaskFAB = findViewById(R.id.addTaskFAB);
        addTaskFAB.setOnClickListener(view -> openTaskInputFragment());
    }

    // Method to open Task Input Fragment
    private void openTaskInputFragment() {
        Log.d("FAB", "FAB Clicked");
        FragmentManager fragmentManager = getSupportFragmentManager();
        TaskInputFragment taskInputFragment = new TaskInputFragment();
        taskInputFragment.setTaskSavedListener(this);
        taskInputFragment.show(fragmentManager, "taskInput");
        Log.d("Main Activity", "Input fragment has been displayed");

    }

    @Override
    public void onTaskSaved(String taskName, String taskDescription) {
        // Add the task to the RecyclerView and update the adapter
        // Add only the task name (or taskDescription if that's your requirement)
        taskList.add(taskName);  // Assuming taskList is List<String>

        // Notify the adapter of the change
        taskAdapter.notifyDataSetChanged();
        Log.d("Main Activity", "New Task has been added");
    }
}
