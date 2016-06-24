package com.example.android.spiderintask2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{


    SensorManager mSensorManager;
    Sensor mProximity;
    int track_number =0;
    MusicPlayer musicPlayer = new MusicPlayer();
    ChangePictureTask gesture_control = new ChangePictureTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                final Spinner spinner = (Spinner)findViewById(R.id.track_selection_spinner);
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                R.array.tracks, android.R.layout.simple_spinner_item);

                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                       int arg2, long arg3) {
                                track_number = spinner.getSelectedItemPosition();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                track_number =0;
                            }
                        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences use_gesture = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(use_gesture.getBoolean("gesture",false)) {
            SensorManager mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
            Sensor mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            mSensorManager.registerListener((SensorEventListener) this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);

            ImageButton play_slideshow = (ImageButton) findViewById(R.id.slideshow_play_button);
            play_slideshow.setVisibility(View.GONE);

            gesture_control.addImages();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id==R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void startTrack(View view){
        startTrack();
    }
    public void startTrack(){
        ImageButton play_button = (ImageButton)findViewById(R.id.play_button);
        play_button.setVisibility(View.GONE);
        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        pause_button.setVisibility(View.VISIBLE);

        musicPlayer.setContext(getApplicationContext());
        musicPlayer.beginPlayer(track_number);
}

    public void stopTrack(View view){
        ImageButton play_button = (ImageButton)findViewById(R.id.play_button);
        play_button.setVisibility(View.VISIBLE);
        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        pause_button.setVisibility(View.GONE);

        musicPlayer.stopPlayer();
    }

    public void startSlideshow(View view) {
        ImageButton play_button = (ImageButton) findViewById(R.id.slideshow_play_button);
        play_button.setVisibility(View.GONE);
        TextView timer_text = (TextView) findViewById(R.id.timer_textview);
        timer_text.setVisibility(View.VISIBLE);

        ChangePictureTask slideshow = new ChangePictureTask();
        slideshow.setActivity(this);
        slideshow.addImages();
        slideshow.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mSensorManager!=null)
            mSensorManager.unregisterListener((SensorEventListener)this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] <= 10) {
                gesture_control.setActivity(this);
                gesture_control.goToNextPicture();
                //Log.i("Sensor :", "Working Fine");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
