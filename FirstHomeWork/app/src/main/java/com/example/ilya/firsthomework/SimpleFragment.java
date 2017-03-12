package com.example.ilya.firsthomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ilya on 08.03.17.
 */
public class SimpleFragment extends Fragment {
    public static final String TAG = SimpleFragment.class.getSimpleName();
    private Button mSave;
    private EditText mFirstName;
    private EditText mSecondName;
    private TextView mTextViewChooseDate;

    //very big kostil((
    class TextWatcherChangedTwo implements TextWatcher {
        TextWatcherChangedTwo() {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final boolean firstEmpty = TextUtils.isEmpty(mFirstName.getText().toString());
            final boolean secondEmpty = TextUtils.isEmpty(mSecondName.getText().toString());
            final boolean dateEmpty = TextUtils.isEmpty(mTextViewChooseDate.getText()) ||
                    mTextViewChooseDate.getText().equals(getString(R.string.choose_data));

            mSave.setEnabled(!firstEmpty && !secondEmpty && !dateEmpty);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final LinearLayout l = (LinearLayout)inflater.inflate(R.layout.simple_fragment, container, false);
        mTextViewChooseDate = (TextView) l.findViewById(R.id.show_dialog_choose_date);

        mSave = (Button) l.findViewById(R.id.button_save);
        mSave.setEnabled(false);
        mFirstName = (EditText) l.findViewById(R.id.edit_text_first_name);
        mSecondName = (EditText) l.findViewById(R.id.edit_text_second_name);
        mSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ViewInfoActivity.class);
            intent.putExtra("first_name", mFirstName.getText().toString());
            intent.putExtra("date_string", mTextViewChooseDate.getText());
            intent.putExtra("second_name", mSecondName.getText().toString());
            System.out.println("!!! send " + getActivity() + " " + mFirstName.getText() + " " + mSecondName.getText() + " " +
                    mTextViewChooseDate.getText());
            getActivity().startActivity(intent);
            }
        });


        final TextWatcherChangedTwo textWatcherChanged = new TextWatcherChangedTwo();
        mFirstName.addTextChangedListener(textWatcherChanged);
        mSecondName.addTextChangedListener(textWatcherChanged);
        mTextViewChooseDate.addTextChangedListener(textWatcherChanged);

        mTextViewChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DateDialogFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });

        return l;
    }
}
