package com.example.voicerecognition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edittext2;
    Button talk;
    SpeechRecognizer mspeechrecognizer;
    Intent mspeechrecognizerintent;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckPermission();
        mRef=FirebaseDatabase.getInstance().getReference();
        edittext2=findViewById(R.id.editText2);
        mspeechrecognizer=SpeechRecognizer.createSpeechRecognizer(this);
        mspeechrecognizerintent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mspeechrecognizerintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mspeechrecognizerintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        if(edittext2.equals("Rex switch on device one")){

        }else{

        }
        if(edittext2.equals("Rex switch on my USB")){

        }else
        {

        }
        mspeechrecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches=results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(matches!=null){
                    edittext2.setText(matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        talk=findViewById(R.id.button);
        talk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        edittext2.setHint("see the input");
                        mspeechrecognizer.stopListening();
                    break;

                    case MotionEvent.ACTION_DOWN:
                        edittext2.setText("");
                        edittext2.setHint("speak");
                        mspeechrecognizer.startListening(mspeechrecognizerintent);
                        break;
                }

                return false;
            }
        });
    }
    private void CheckPermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
if(!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED)){
    Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName()));
    startActivity(intent);
    finish();
}
        }

    }
}
