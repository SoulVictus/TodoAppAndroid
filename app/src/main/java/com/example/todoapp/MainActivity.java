package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.database.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TodosRecyclerViewAdapter recyclerViewAdapter;
    TodoViewModel viewModel;

    FloatingActionButton addTodoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new TodoViewModel(getApplication());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewAdapter = new TodosRecyclerViewAdapter(this, viewModel);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel.getTodoList().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                recyclerViewAdapter.setViewData(todos);
//                if (todos == null || todos.isEmpty()) {
//                    viewModel.createDummyData();
//                }
//                else {
//                    recyclerViewAdapter.setViewData(todos);
//                }
            }
        });

        addTodoButton = findViewById(R.id.addTodoButton);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoFragment fragment = new AddTodoFragment();
                fragment.show(getSupportFragmentManager(), "AddTodoFragment");
            }
        });
    }
}