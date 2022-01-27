package com.example.todoapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.todoapp.database.Todo;

import java.util.List;

public class TodoDetailsActivity extends AppCompatActivity {

    TodoViewModel viewModel;
    Todo currentTodo;

    TextView titleTextView;
    TextView descriptionTextView;
    TextView dateTextView;
    CheckBox isDoneCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_details);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        dateTextView = findViewById(R.id.dateTextView);
        isDoneCheckBox = findViewById(R.id.isDoneCheckBox);

        viewModel = new TodoViewModel(getApplication());

        Intent intent = getIntent();
        int postion = intent.getIntExtra("todoPosition", 0);

        viewModel.getTodoList().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                currentTodo = todos.get(postion);
                titleTextView.setText(currentTodo.getTitle());
                titleTextView.setTextColor(
                        currentTodo.isDone() ?
                                getResources().getColor(R.color.design_default_color_secondary)
                                : getResources().getColor(R.color.black));

                descriptionTextView.setText(currentTodo.getDescription());
                dateTextView.setText(currentTodo.getFormattedDate());
                isDoneCheckBox.setChecked(currentTodo.isDone());
            }
        });

        isDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentTodo.setDone(isChecked);
                viewModel.updateTodo(currentTodo);
            }
        });
    }


}
