package com.example.android.spiderintask2;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Srikanth on 6/23/2016.
 */
public class MusicPlayer {

    private Context mContext;
    MediaPlayer mediaPlayer;

    public void setContext(Context context){
        mContext = context;
    }

    public void beginPlayer(int track_number) {
        switch (track_number) {
            case 0: {
                mediaPlayer = MediaPlayer.create(mContext, R.raw.cake_face);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            }
            case 1: {
                mediaPlayer = android.media.MediaPlayer.create(mContext, R.raw.land_on_mars);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            }
            case 2: {
                mediaPlayer = android.media.MediaPlayer.create(mContext, R.raw.popcorn);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            }
            case 3: {
                mediaPlayer = android.media.MediaPlayer.create(mContext, R.raw.short_man);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                break;
            }
            default:
                Log.e("Media Player", "Invalid track number received");
        }
    }

    public void stopPlayer(){
        mediaPlayer.release();
    }
}
