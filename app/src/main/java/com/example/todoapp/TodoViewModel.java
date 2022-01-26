package com.example.todoapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.database.Todo;

import java.util.List;

public class TodoViewModel extends ViewModel {
    private LiveData<List<Todo>> todoList;
    private TodosRepository repository;

    public TodoViewModel(Application application) {
        repository = new TodosRepository(application);
        todoList = repository.getTodosList();
    }

    LiveData<List<Todo>> getTodoList() {
        return todoList;
    }

    void addTodo(Todo todo) {
        repository.addTodo(todo);
    }

    void updateTodo(Todo todo) {
        repository.updateTodo(todo);
    }

    void deleteTodo(Todo todo) {
        repository.deleteTodo(todo);
    }

    public void createDummyData() {
        repository.createDummyDataset();
    }
}
