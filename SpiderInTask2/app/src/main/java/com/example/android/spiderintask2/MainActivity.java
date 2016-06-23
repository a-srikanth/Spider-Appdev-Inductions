package com.example.android.spiderintask2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int track_number =0;
    MusicPlayer musicPlayer = new MusicPlayer();

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
}
