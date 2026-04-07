package com.example.audiovideoplayerapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    EditText urlInput;

    Button openUrlBtn, playVideoBtn, pauseVideoBtn, stopVideoBtn, restartVideoBtn;
    Button openAudioBtn, playAudioBtn, pauseAudioBtn, stopAudioBtn, restartAudioBtn;

    MediaPlayer mediaPlayer;
    Uri audioUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        urlInput = findViewById(R.id.urlInput);

        openUrlBtn = findViewById(R.id.openUrlBtn);
        playVideoBtn = findViewById(R.id.playVideoBtn);
        pauseVideoBtn = findViewById(R.id.pauseVideoBtn);
        stopVideoBtn = findViewById(R.id.stopVideoBtn);
        restartVideoBtn = findViewById(R.id.restartVideoBtn);

        openAudioBtn = findViewById(R.id.openAudioBtn);
        playAudioBtn = findViewById(R.id.playAudioBtn);
        pauseAudioBtn = findViewById(R.id.pauseAudioBtn);
        stopAudioBtn = findViewById(R.id.stopAudioBtn);
        restartAudioBtn = findViewById(R.id.restartAudioBtn);

        // VIDEO LOGIC
        openUrlBtn.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            videoView.setVideoURI(Uri.parse(url));
        });

        playVideoBtn.setOnClickListener(v -> videoView.start());

        pauseVideoBtn.setOnClickListener(v -> videoView.pause());

        stopVideoBtn.setOnClickListener(v -> videoView.stopPlayback());

        restartVideoBtn.setOnClickListener(v -> videoView.seekTo(0));

        // AUDIO LOGIC
        openAudioBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, 1);
        });

        playAudioBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.start();
        });

        pauseAudioBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.pause();
        });

        stopAudioBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });

        restartAudioBtn.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            audioUri = data.getData();

            mediaPlayer = MediaPlayer.create(this, audioUri);
            Toast.makeText(this, "Audio Loaded", Toast.LENGTH_SHORT).show();
        }
    }
}