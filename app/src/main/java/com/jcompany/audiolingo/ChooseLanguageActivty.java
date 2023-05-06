package com.jcompany.audiolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class ChooseLanguageActivty extends AppCompatActivity {
    Button button_french,button_spanish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language_activty);
        button_french = findViewById(R.id.button_french);
        button_spanish = findViewById(R.id.button_spanish);
        button_french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "1";
                Intent game = new Intent(ChooseLanguageActivty.this,ChooseTextActivty.class);
                game.putExtra("key",value);
                startActivity(game);
            }
        });


        button_spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = "2";
                Intent game = new Intent(ChooseLanguageActivty.this,ChooseTextActivty.class);
                game.putExtra("key",value);
                startActivity(game);
            }
        });
    }
}