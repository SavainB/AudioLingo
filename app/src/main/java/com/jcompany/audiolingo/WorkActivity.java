package com.jcompany.audiolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkActivity extends AppCompatActivity {
    Button button_listen,button_verify,button_wait;
    TextView textview;
    EditText edittext_response;
    TextToSpeech textToSpeech;
    private int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            String value_id = extras.getString("key_id");
            //The key argument here must match that used in the other activity
            button_listen = findViewById(R.id.button_listen);
            textview = findViewById(R.id.textview);
            edittext_response = findViewById(R.id.edittext_response);
            button_verify = findViewById(R.id.button_verify);
            ArrayList<String> textUserToArray = new ArrayList<String>();
            ArrayList<String> textToArray = new ArrayList<String>();
            textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        if ( value_id.equals("1")) {
                            textToSpeech.setLanguage(Locale.UK);
                        }
                        else {
                            textToSpeech.setLanguage(Locale.ITALIAN);
                        }

                    }

                }
            });

            button_listen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textToSpeech.isSpeaking()) {
                        button_listen.setText("Ecouter");
                        textToSpeech.stop();
                    }
                    else {
                        button_listen.setText("Arreter");
                        textToSpeech.speak(value, TextToSpeech.QUEUE_ADD, null);
                    }

                }
            });
            button_verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userInput  = edittext_response.getText().toString();
                    ArrayList<String> userInputWords = splitIntoWords(userInput);
                    ArrayList<String> expectedWords = splitIntoWords(value);
                    if (userInputWords.equals(expectedWords)){
                        textview.setText("Vous avez raison");
                    }
                    else
                    {
                        textview.setText("Vous avez tort \n"+userInput +"\n"+value);
                    }


                }
            });
        }
    }

    // La méthode prend la phrase "Hello world! How are you today?" en entrée.
    public static ArrayList<String> splitIntoWords(String text) {

        // On crée une liste vide qui va contenir les mots extraits de la phrase.
        ArrayList<String> words = new ArrayList<>();

        // On compile une expression régulière qui va servir à extraire les mots de la phrase.
        Pattern pattern = Pattern.compile("\\p{L}+|\\d+");

        // On crée un objet Matcher qui va chercher les mots dans la phrase en utilisant l'expression régulière compilée.
        Matcher matcher = pattern.matcher(text);

        // On parcourt tous les mots trouvés dans la phrase.
        while (matcher.find()) {

            // On récupère le mot courant.
            String word = matcher.group();

            // On ajoute le mot courant à la liste de mots.
            words.add(word.toLowerCase());
        }

        // On renvoie la liste de mots extraits de la phrase.
        return words;
    }

    // On peut appeler la méthode ainsi :
    String phrase = "Hello world! How are you today?";
    ArrayList<String> mots = splitIntoWords(phrase);


}
