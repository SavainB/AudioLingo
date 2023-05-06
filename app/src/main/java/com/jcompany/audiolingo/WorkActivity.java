package com.jcompany.audiolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkActivity extends AppCompatActivity {
    Button button_listen,button_verify;
    TextView textview;
    EditText edittext_response;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
            button_listen = findViewById(R.id.button_listen);
            textview = findViewById(R.id.textview);
            edittext_response = findViewById(R.id.edittext_response);
            button_verify = findViewById(R.id.button_verify);
            textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.UK);
                    }
                }
            });
            button_listen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textToSpeech.speak(value, TextToSpeech.QUEUE_FLUSH, null);
                }
            });
            button_verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = edittext_response.getText().toString();
                    if (text.equals(value)){
                        textview.setText("Vous avez raison");
                    }
                    else
                    {
                        textview.setText("Vous avez tort \n"+text+"\n"+value);
                    }


                }
            });
        }
    }
}