package com.example.android.spidertask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int display_num = 0;

    //Function called when click button is hit.
    //Increments the number by 1
    public void increment(View view){
        display_num++;
        TextView number = (TextView)findViewById(R.id.number_textview);
        number.setText(String.valueOf(display_num));
    }

    //resets the number to 0
    public void reset(View view){
        display_num=0;
        TextView number = (TextView)findViewById(R.id.number_textview);
        number.setText(String.valueOf(display_num));
    }
}
