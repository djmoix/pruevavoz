package com.example.proyectovoz2;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener {
    private  AIService mAIService;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final AIConfiguration config = new AIConfiguration ("57c23b09147f469a99fcc985a6d40fa5",
          AIConfiguration.SupportedLanguages.Spanish,
          AIConfiguration.RecognitionEngine.System);
    mAIService = AIService.getService(this, config);
    mAIService.setListener(this);

    mTextToSpeech= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {

        }
    });
    findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAIService.startListening();
        }
    });


    }


    @Override
    public void onResult(AIResponse response) {
        Result result1=response.getResult();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech.speak((CharSequence) result1.getFulfillment(), TextToSpeech.QUEUE_FLUSH, null, null );
        }
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
