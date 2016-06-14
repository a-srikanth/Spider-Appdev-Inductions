package com.example.android.spiderintask1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConfirmationActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView textView = (TextView) findViewById(R.id.thanks_textview);
        textView.setText("Thank you "+name+" for your response!!");

        TextView timestamp = (TextView) findViewById(R.id.timestamp_textview);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        Date current_date_time = new Date();
        String current_datestamp = dateFormat.format(current_date_time);
        String current_timestamp = timeFormat.format(current_date_time);
        timestamp.setText("Submitted on:\n"+current_datestamp+" at "+current_timestamp);
    }




}
