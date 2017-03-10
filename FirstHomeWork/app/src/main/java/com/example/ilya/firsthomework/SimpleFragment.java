package com.example.ilya.firsthomework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.attr.button;
import static android.R.attr.dependency;

/**
 * Created by ilya on 08.03.17.
 */
public class SimpleFragment extends Fragment {
    public static final String TAG = SimpleFragment.class.getSimpleName();

    //very big kostil((
    public class TextWatcherChangedTwo implements TextWatcher {
        Editable[] texts;
        boolean[] exists;
        boolean[] isNotEmpty;
        Button dependButton;
        TextView textDate;
        TextWatcherChangedTwo(Button button, TextView date) {
            texts = new Editable[2];
            exists = new boolean[2];
            isNotEmpty = new boolean[2];
            dependButton = button;
            textDate = date;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!exists[0]) {
                exists[0] = true;
                isNotEmpty[0] = s.length() != 0;
                texts[0] = s;
            } else if (texts[0] != s) {
                exists[1] = true;
                isNotEmpty[1] = s.length() != 0;
                texts[1] = s;
            } else {
                isNotEmpty[0] = s.length() != 0;
            }
            if (exists[0] && exists[1] && !textDate.getText().equals("choose data")) { // I couldnt else
                System.out.println(textDate.getText());
                dependButton.setActivated(true);
            } else {
                dependButton.setActivated(false);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final LinearLayout l = (LinearLayout)inflater.inflate(R.layout.simple_fragment, container, false);
        final TextView textViewChooseDate = (TextView) l.findViewById(R.id.show_dialog_choose_date);

        final Button buttonSave = (Button) l.findViewById(R.id.button_save);
        buttonSave.setActivated(false);
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buttonSave.isActivated()) {
                    buttonSave.setTextColor(1000);
                }
            }
        });

        final EditText textEditFirstName = (EditText) l.findViewById(R.id.edit_text_first_name);
        final EditText textEditSecondName = (EditText) l.findViewById(R.id.edit_text_second_name);
        final TextWatcherChangedTwo textWatcherChanged = new TextWatcherChangedTwo(buttonSave, textViewChooseDate);
        textEditFirstName.addTextChangedListener(textWatcherChanged);
        textEditSecondName.addTextChangedListener(textWatcherChanged);

        textViewChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DateDialogFragment(textViewChooseDate, textWatcherChanged, buttonSave);
                newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });

        return l;
    }
}
