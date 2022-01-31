package com.example.todoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.todoapp.database.Todo;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class AddTodoFragment extends DialogFragment {
    TodoViewModel viewModel;

    Button addButton;
    Button cancelButton;
    EditText titleInput;
    EditText descriptionInput;
    CalendarView dateInput;

    Date date = new Date();

    public AddTodoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View inflatedView = inflater.inflate(R.layout.todo_add_fragment, container, false);
        viewModel = new TodoViewModel(getActivity().getApplication());

        addButton = (Button) inflatedView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                if (title.isEmpty())
                    title = getResources().getString(R.string.default_todo_title);
                String description = descriptionInput.getText().toString();
                viewModel.addTodo(new Todo(title, description, date, false));
                closeFragment();
            }
        });

        cancelButton = (Button) inflatedView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });


        titleInput = (EditText) inflatedView.findViewById(R.id.titleInput);
        descriptionInput = (EditText) inflatedView.findViewById(R.id.descriptionInput);

        dateInput = (CalendarView) inflatedView.findViewById(R.id.dateInput);
        dateInput.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateString = String.format(Locale.ENGLISH, "%d-%d-%d", dayOfMonth, month+1, year);
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    date = sfd.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return inflatedView;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
