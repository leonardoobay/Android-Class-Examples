package com.sargent.mark.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by mark on 7/4/17.
 */

public class AddToDoFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    private CheckBox checkBox;
    // Added new variable to filter the list
    private String category;
    private EditText toDo;
    private Spinner genre;
    private DatePicker dp;
    private Button add;
    private final String TAG = "addtodofragment";

    public AddToDoFragment() {
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnDialogCloseListener {
        void closeDialog(int year, int month, int day, String description, String category);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);


        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        toDo = (EditText) view.findViewById(R.id.toDo);


        genre = (Spinner) view.findViewById(R.id.spinner2);

        // Set the listener to spinner
        genre.setOnItemSelectedListener(this);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.categories_add_array, android.R.layout.simple_spinner_dropdown_item);


        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Set the spinner to the adapter
        genre.setAdapter(adapter);

        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dp.updateDate(year, month, day);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogCloseListener activity = (OnDialogCloseListener) getActivity();
                activity.closeDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(), category);
                AddToDoFragment.this.dismiss();
            }
        });

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = genre.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}


