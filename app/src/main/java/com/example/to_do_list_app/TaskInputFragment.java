package com.example.to_do_list_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class TaskInputFragment extends DialogFragment {

    private EditText taskNameInput;
    private EditText taskDescriptionInput;
    private Button saveTaskButton;

    private OnTaskSavedListener taskSavedListener;

    public interface OnTaskSavedListener {
        void onTaskSaved(String taskName, String taskDescription);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);

        taskNameInput = view.findViewById(R.id.taskNameInput);
        taskDescriptionInput = view.findViewById(R.id.taskDescriptionInput);
        saveTaskButton = view.findViewById(R.id.saveTaskButton);

        saveTaskButton.setOnClickListener(v -> {
            String taskName = taskNameInput.getText().toString();
            if(taskName.equals("")){
                Toast.makeText(getContext(), "Task name must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            String taskDescription = taskDescriptionInput.getText().toString();
            if (taskSavedListener != null) {
                taskSavedListener.onTaskSaved(taskName, taskDescription);
            }
            dismiss();
        });

        return view;
    }

    public void setTaskSavedListener(OnTaskSavedListener listener) {
        this.taskSavedListener = listener;
    }
    private void saveTask() {
        String taskName = taskDescriptionInput.getText().toString();
        // Only pass the task name, as taskList is of type List<String>
        if (taskSavedListener != null) {
            taskSavedListener.onTaskSaved(taskName, "");  // Pass taskName, and an empty string for taskDescription if not used
        }
        dismiss();  // Close the fragment dialog after saving the task
    }

}
