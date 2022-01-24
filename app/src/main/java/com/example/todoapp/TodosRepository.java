package com.example.todoapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.database.Todo;
import com.example.todoapp.database.TodosDao;
import com.example.todoapp.database.TodosDatabase;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

public class TodosRepository {
    private TodosDao todosDao;
    private LiveData<List<Todo>> todosList;

    public TodosRepository(Application application) {
        TodosDatabase db = TodosDatabase.getInstance(application);
        todosDao = db.todosDao();
        todosList = todosDao.getTodos();
    }

    public LiveData<List<Todo>> getTodosList() {
        return todosList;
    }

    public void createDummyDataset() {
        for (int i = 0; i < 10; i++) {
            String title = MessageFormat.format("Title {0}", i);
            String description =  "";
            Date date = new Date();
            boolean done = false;
            Todo newTodo = new Todo(title, description, date, done);
            todosDao.insertTodo(newTodo);
        }
        todosList = todosDao.getTodos();
    }
}
