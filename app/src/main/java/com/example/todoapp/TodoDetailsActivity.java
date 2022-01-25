package com.example.todoapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_details);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        dateTextView = findViewById(R.id.dateTextView);

        viewModel = new TodoViewModel(getApplication());

        Intent intent = getIntent();
        int postion = intent.getIntExtra("todoPosition", 0);

        viewModel.getTodoList().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                currentTodo = todos.get(postion);
                titleTextView.setText(currentTodo.getTitle());
                descriptionTextView.setText(currentTodo.getDescription());
                dateTextView.setText(currentTodo.getDate().toString());
            }
        });


    }
}
