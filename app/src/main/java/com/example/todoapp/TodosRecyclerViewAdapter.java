package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.database.Todo;

import org.w3c.dom.Text;

import java.util.List;

public class TodosRecyclerViewAdapter extends RecyclerView.Adapter<TodosRecyclerViewAdapter.ViewHolder>{
    private List<Todo> todoList;
    private Context context;
    private TodoViewModel viewModel;

    public TodosRecyclerViewAdapter(Context context, TodoViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private CheckBox isDoneCheckBox;
        private ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.todoTitleTextView);
            isDoneCheckBox = (CheckBox) view.findViewById(R.id.isDoneListCheckBox);
            deleteButton = (ImageButton) view.findViewById(R.id.deleteImageButton);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public CheckBox getIsDoneCheckBox() { return isDoneCheckBox; }

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
        Todo currentTodo = todoList.get(viewHolder.getAdapterPosition());
        viewHolder.getTitleTextView().setText(currentTodo.getTitle());
        viewHolder.getTitleTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsActivityIntent = new Intent(context, TodoDetailsActivity.class);
                detailsActivityIntent.putExtra("todoPosition", viewHolder.getAdapterPosition());
                context.startActivity(detailsActivityIntent);
            }
        });

        viewHolder.getIsDoneCheckBox().setOnCheckedChangeListener(null);
        viewHolder.getIsDoneCheckBox().setChecked(currentTodo.isDone());
        viewHolder.getIsDoneCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentTodo.setDone(isChecked);
                viewModel.updateTodo(currentTodo);
            }
        });

        viewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteTodo(currentTodo);
            }
        });
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
