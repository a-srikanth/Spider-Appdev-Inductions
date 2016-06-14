package com.example.android.spiderintask1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int spinnerPositionFinder=0;
    String dept,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner)findViewById(R.id.dept_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                dept = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    public int validate(){
        EditText input_name = (EditText)findViewById(R.id.name_text);
        name = input_name.getText().toString();

        EditText input_roll = (EditText)findViewById(R.id.roll_text);
        String roll = input_roll.getText().toString();

        CheckBox p1 = (CheckBox)findViewById(R.id.profile1);
        CheckBox p2 = (CheckBox)findViewById(R.id.profile2);
        CheckBox p3 = (CheckBox)findViewById(R.id.profile3);
        CheckBox p4 = (CheckBox)findViewById(R.id.profile4);
        boolean app_dev = p1.isChecked();
        boolean web_dev = p2.isChecked();
        boolean tronix = p3.isChecked();
        boolean algo = p4.isChecked();

        RadioGroup mentorship = (RadioGroup)findViewById(R.id.mentorship_radio_group);
        int radio_id = mentorship.getCheckedRadioButtonId();
        String reqd_mentor;
        if(radio_id==0){
            reqd_mentor = "yes";
        }else if(radio_id==1){
            reqd_mentor="no";
        }

        if(name.length()==0){
            Toast.makeText(getApplicationContext(),"Enter a name",Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(roll.length()!=9){
            Toast.makeText(getApplicationContext(),"Enter a valid roll number",Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(app_dev==false && web_dev==false && tronix==false && algo==false){
            Toast.makeText(getApplicationContext(),"Select atleast one profile",Toast.LENGTH_SHORT).show();
            return 1;
        }

    return 0;
    }

    public void submit(View view){
        int validation_flag = validate();
        if(validation_flag==0){
            Intent intent = new Intent(getBaseContext(),ConfirmationActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    }


}
