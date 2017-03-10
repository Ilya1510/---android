package com.example.ilya.firsthomework;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ilya on 08.03.17.
 */
public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = DateDialogFragment.class.getSimpleName();
    private TextView PARENT;
    private SimpleFragment.TextWatcherChangedTwo textWatcher;
    private Button dependButton;

    public DateDialogFragment(TextView parent,
                              SimpleFragment.TextWatcherChangedTwo textWatcherChangedTwo,
                              Button button) {
        super();
        PARENT = parent;
        dependButton = button;
        textWatcher = textWatcherChangedTwo;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DatePicker datePicker = new DatePicker(getContext());
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        PARENT.setText(dayOfMonth + "." + month + "." + year);
        if (textWatcher.isNotEmpty[0] && textWatcher.isNotEmpty[1]) {
            dependButton.setActivated(true);
        } else {
            dependButton.setActivated(false);
        }
    }
}
