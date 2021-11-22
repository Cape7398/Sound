package com.example.sound;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AudioPlayer extends Activity {

    private SoundPool mSoundPool;
    private int mSoundId = 1;
    private int mStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mSoundPool.load(this, R.raw.sound, 1);

        Button playButton = findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(onPlayButtonClickListener);
    }

    Button.OnClickListener onPlayButtonClickListener
            = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float leftVolume = curVolume / maxVolume;
            float rightVolume = curVolume / maxVolume;
            int priority = 1;
            int no_loop = 0;
            float normal_playback_rate = 1f;
            mStreamId = mSoundPool.play(mSoundId, leftVolume, rightVolume, priority, no_loop,
                    normal_playback_rate);

            Toast.makeText(getApplicationContext(),
                    "soundPool.play()",
                    Toast.LENGTH_LONG).show();
        }
    };

    Button.OnClickListener onPauseButtonClickListener
            = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSoundPool.pause(mStreamId);
            Toast.makeText(getApplicationContext(),
                    "soundPool.pause()",
                    Toast.LENGTH_LONG).show();
        }
    };
}

