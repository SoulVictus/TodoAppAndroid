package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.database.Todo;

import java.util.List;

public class TodosRecyclerViewAdapter extends RecyclerView.Adapter<TodosRecyclerViewAdapter.ViewHolder>{
    private List<Todo> todoList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private ImageButton editButton;
        private ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.todoTitleTextView);
            editButton = (ImageButton) view.findViewById(R.id.editImageButton);
            deleteButton = (ImageButton) view.findViewById(R.id.deleteImageButton);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public ImageButton getEditButton() {
            return editButton;
        }

        public ImageButton getDeleteButton() {
            return deleteButton;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(todoList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (todoList != null) {
            return todoList.size();
        }
        return 0;
    }

    public void setViewData(List<Todo> todos) {
        this.todoList = todos;
        notifyDataSetChanged();
    }
}