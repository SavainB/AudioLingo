package com.jcompany.audiolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseTextActivty extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_text_activty);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
            List<TextItem> textItems = new ArrayList<>();
            if (value.equals("1")){
                textItems.add(new TextItem("sarah","my name is sarah"));
                textItems.add(new TextItem("heur","my name is heur"));
                textItems.add(new TextItem("peur","my name is peur"));
            }

            else{
                textItems.add(new TextItem("sarah", "mi nombre es  sarah"));
                textItems.add(new TextItem("heur", "mi nombre es  heur"));
                textItems.add(new TextItem("peur", "mi nombre es  peur"));
            }
            ListView textList = findViewById(R.id.list_text );
            textList.setAdapter(new TextItemAdapter(this,textItems));
        }
    }
}