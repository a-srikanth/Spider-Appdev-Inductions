package com.example.android.spiderintask2;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Srikanth on 6/23/2016.
 */
public class ChangePictureTask extends AsyncTask<String, Integer, String[]> {

    private Activity mActivity;
    ArrayList<Integer> imageIds = new ArrayList<Integer>();

    public void setActivity(Activity activity){
        mActivity = activity;
    }

    public void addImages(){
        imageIds.add(0,R.drawable.concept_car_zero);
        imageIds.add(1,R.drawable.concept_car_one);
        imageIds.add(2,R.drawable.concept_car_two);
        imageIds.add(3,R.drawable.concept_car_three);
        imageIds.add(4,R.drawable.concept_car_four);
        imageIds.add(5,R.drawable.concept_car_five);
        imageIds.add(6,R.drawable.concept_car_six);
        imageIds.add(7,R.drawable.concept_car_seven);
        imageIds.add(8,R.drawable.concept_car_eight);
    }

    @Override
    protected String[] doInBackground(String... params) {

        for(int i=0 ; i<imageIds.size() ; i++) {
            publishProgress(i,3);
            for(int j=2 ; j>=0 ; j--) {
                long millis = 1000;
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i,j);
            }
        }
        return params;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        ImageView current_image = (ImageView)mActivity.findViewById(R.id.slideshow_imageview);
        current_image.setImageResource(imageIds.get(values[0]));

        TextView timer_textview = (TextView)mActivity.findViewById(R.id.timer_textview);
        timer_textview.setText(Integer.toString(values[1]));
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String[] strings) {
        ImageButton play_button = (ImageButton) mActivity.findViewById(R.id.slideshow_play_button);
        play_button.setVisibility(View.VISIBLE);
        TextView timer_text = (TextView)mActivity.findViewById(R.id.timer_textview);
        timer_text.setVisibility(View.GONE);

        super.onPostExecute(strings);
    }

}
