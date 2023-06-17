package com.jcompany.audiolingo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkActivity extends AppCompatActivity {
    Button button_listen,button_verify,button_correction;
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
            button_correction = findViewById(R.id.button_correction);
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
            button_correction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userInput  = edittext_response.getText().toString();
                    ArrayList<String> userInputWords = splitIntoWords(userInput);
                    ArrayList<String> expectedWords = splitIntoWords(value);
                    String texte = verification(userInputWords,expectedWords);
                    SpannableString spannableString = new SpannableString(texte);
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View view) {
                            // Action à effectuer lorsque le texte cliquable est cliqué
                            Toast.makeText(getApplicationContext(), "Texte cliqué", Toast.LENGTH_SHORT).show();
                        }
                    };
                    spannableString.setSpan(clickableSpan, 0, texte.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textview.setMovementMethod(LinkMovementMethod.getInstance());
                    textview.setText(spannableString);
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
                    String texte = verificationPendue(userInputWords,expectedWords).toString();
                    if (userInputWords.equals(expectedWords)){
                        textview.setText("vous avez raison \n"+texte);
                    }
                    else
                    {
                        textview.setText("vous avez tort \n"+texte);
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
    /**
     * Vérifie les mots saisis par l'utilisateur et les compare aux mots attendus.
     * Remplace les espaces par des points entre les mots dans le résultat.
     *
     * @param userInputWords   Liste des mots saisis par l'utilisateur.
     * @param expectedWords    Liste des mots attendus.
     * @return Le résultat avec les espaces remplacés par des points entre les mots.
     */
    public static String verificationPendue(ArrayList<String> userInputWords, ArrayList<String> expectedWords) {
        StringBuilder result = new StringBuilder();

        for (String expectedWord : expectedWords) {
            boolean found = false;
            for (String userInputWord : userInputWords) {
                StringBuilder currentWord = new StringBuilder();
                boolean wordFound = true;
                int length = Math.min(expectedWord.length(), userInputWord.length());
                for (int i = 0; i < length; i++) {
                    if (expectedWord.charAt(i) == userInputWord.charAt(i)) {
                        currentWord.append(userInputWord.charAt(i));
                    } else {
                        currentWord.append("_ ");
                        wordFound = false;
                    }
                }
                if (wordFound) {
                    found = true;
                    result.append(currentWord).append(" ");
                    break;
                }
            }
            if (!found) {
                for (int i = 0; i < expectedWord.length(); i++) {
                    if (expectedWord.charAt(i) == ' ') {
                        result.append(".");
                    } else {
                        result.append("_");
                    }
                }
                result.append(",");  // Ajoute un espace entre les mots
            }
        }
        return result.toString();
    }


    public static String verification(ArrayList<String> userInputWords, ArrayList<String> expectedWords) {
        ArrayList<String> newlist = new ArrayList<>();

        for (String expectedWord : expectedWords) {
            boolean found = false;
            for (String userInputWord : userInputWords) {
                if (userInputWord.equals(expectedWord)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                newlist.add(expectedWord);
            }
        }
        //Collections.shuffle(newlist);
        return  newlist.toString();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }

}
