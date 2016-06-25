package com.example.android.spiderintask2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
            publishProgress(i,3,1);
            for(int j=2 ; j>=0 ; j--) {
                long millis = 1000;
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i,j,0);
            }
        }
        return params;
    }

    @Override
    protected void onProgressUpdate(final Integer... values) {
        if(values[2]==1) {
            final ImageView current_image = (ImageView) mActivity.findViewById(R.id.slideshow_imageview);
            if(getAnimationPreference(mActivity))
                useAnimation(mActivity,current_image,values[0]);
            else
                current_image.setImageResource(imageIds.get(values[0]));
        }
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

    public void useAnimation(Context context, final ImageView imageView, final int imageID){

        final Animation anim_out = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        final Animation anim_in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setImageResource(imageIds.get(imageID));
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }
                });
                imageView.startAnimation(anim_in);
            }
        });
        imageView.startAnimation(anim_out);

    }

    int counter = 0;
    public void goToNextPicture(){
        if(counter <imageIds.size()){
            counter++;
            if(counter ==imageIds.size()) counter =0;
            try {
                ImageView imageView = (ImageView) mActivity.findViewById(R.id.slideshow_imageview);
                if (getAnimationPreference(mActivity))
                    useAnimation(mActivity, imageView, counter);
                else
                    imageView.setImageResource(imageIds.get(counter));
            }catch (NullPointerException e){
                Log.e("goToNextPicture:",e.toString());
            }
        }
    }


    public static Boolean getAnimationPreference(Context context){
        SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(context);
        return myPref.getBoolean("animate",true);
    }
}
