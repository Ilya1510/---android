package com.example.ilya.firsthomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ilya on 11.03.17.
 */
public class ViewInfoActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras(); //Thank stackOverFlow)
        String firstName = bundle.getString("first_name");
        String date = bundle.getString("date_string");
        String secondName = bundle.getString("second_name");

        System.out.println("!!!! get " + firstName + " " + secondName + " " + date);
        TextView textViewFirstName = (TextView) findViewById(R.id.text_view_first_name);
        TextView textViewSecondName = (TextView) findViewById(R.id.text_view_second_name);
        TextView textViewDate = (TextView) findViewById(R.id.text_view_choose_date);
        textViewFirstName.setText(firstName);
        textViewSecondName.setText(secondName);
        textViewDate.setText(date);

        Button editButton = (Button) findViewById(R.id.button_edit_data);
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            //Here you need to fill intent with data
            Intent intent = new Intent(ViewInfoActivity.this, MainActivity.class);
            startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
