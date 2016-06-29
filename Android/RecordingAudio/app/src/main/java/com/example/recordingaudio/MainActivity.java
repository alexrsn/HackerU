package com.example.recordingaudio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity {

    Button btnStartRecording;
    AudioRecorder audioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartRecording = (Button) findViewById(R.id.btnStartRecording);
        audioRecorder = new AudioRecorder();

        File externalStorage = getExternalFilesDir(null);
        audioRecorder.setExternalStorageDir(externalStorage);
    }

    public void btnStartRecording(View view) {
        if (!audioRecorder.isRecording()) {
            audioRecorder.start();
            btnStartRecording.setText("Stop Recording");
        } else {
            audioRecorder.stopRecording();
            btnStartRecording.setText("Start Recording");
        }
    }

    public void btnPlayRecording(View view) {
        audioRecorder.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audioRecorder != null && audioRecorder.isRecording()) {
            audioRecorder.stopRecording();
        }
    }
}
