package com.example.todoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.todoapp.database.Todo;

import java.util.Date;

public class AddTodoFragment extends DialogFragment {
    TodoViewModel viewModel;

    Button addButton;
    Button cancelButton;

    EditText titleInput;
    EditText descriptionInput;
    EditText dateInput;

    public AddTodoFragment(TodoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View inflatedView = inflater.inflate(R.layout.todo_add_fragment, container, false);

        addButton = (Button) inflatedView.findViewById(R.id.addButton);
        cancelButton = (Button) inflatedView.findViewById(R.id.cancelButton);
        titleInput = (EditText) inflatedView.findViewById(R.id.titleInput);
        descriptionInput = (EditText) inflatedView.findViewById(R.id.descriptionInput);
        dateInput = (EditText) inflatedView.findViewById(R.id.editTextDate);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                Date date = new Date();
                viewModel.addTodo(new Todo(title, description, date, false));
                closeFragment();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });

        return inflatedView;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}
