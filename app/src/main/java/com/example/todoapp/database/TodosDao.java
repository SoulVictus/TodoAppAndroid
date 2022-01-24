package com.example.todoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodosDao {
    @Insert
    void insertAll(Todo... todos);

    @Insert
    void insertTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Query("SELECT * FROM todos")
    LiveData<List<Todo>> getTodos();

}
