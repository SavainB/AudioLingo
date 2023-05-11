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
                textItems.add(new TextItem("My name is Sarah - Nice to meet you!","Hi, I’m Sarah. I’m eight and love blue. I like dolls, drawing, and family time. I’m a bit shy but love new friends. Nice to meet you!","1"));
                textItems.add(new TextItem("At the park","I love the park with friends. We play, kick a ball, picnic. It's fun to run around and feel the sunshine on our faces.","1"));
                textItems.add(new TextItem("My favorite color","Blue is my favorite color. It's the color of the sky on a sunny day, and it makes me feel happy. I love wearing blue clothes and decorating my room with blue things. Blue is the best color!","1"));
                textItems.add(new TextItem("Going to the beach ", "I love going to the beach. The sand is warm and soft, and the water is refreshing. I like building sandcastles, collecting shells, and swimming in the waves. Let's pack our bags and go to the beach!","1"));
                textItems.add(new TextItem("A day at school", "I go to school every day. I learn math, reading, science, and more. I have recess with my friends and eat lunch in the cafeteria. School can be challenging, but it's also fun. I enjoy learning new things!","1"));
                textItems.add(new TextItem("Playing with my dog", "I have a dog named Max. He's furry and friendly, and we play together every day. We like to go for walks, chase each other around the yard, and cuddle on the couch. Max is my best friend!","1"));
                textItems.add(new TextItem("My family ", "I have a mom, dad, and little brother. We love spending time together, whether it's playing games, watching movies, or going on adventures. My family is the best, and I'm grateful for them every day.","1"));
                textItems.add(new TextItem("In the garden", "I like gardening with my mom. We plant flowers, vegetables, and herbs. We water them and watch them grow. It's fun to see the results of our hard work and enjoy the beauty of nature.","1"));
                textItems.add(new TextItem("Eating pizza", "Pizza is my favorite food. I like it with pepperoni, mushrooms, and extra cheese. It's crispy, cheesy, and delicious. I could eat pizza every day!","1"));
                textItems.add(new TextItem("savain","savain the best","1"));
            }

            else{
                textItems.add(new TextItem("Mi nombre es Sofia", "Hola, soy Sofia. Tengo ocho años y vivo con mi mamá, papá y mi hermanito. Me gusta jugar con mis muñecas, dibujar y pasar tiempo con mi familia. Mi color favorito es el azul y mi comida favorita es la pizza. Soy un poco tímida al principio, pero me encanta hacer nuevos amigos. ¡Mucho gusto!","2"));
                textItems.add(new TextItem("En el parque", "Me encanta ir al parque con mis amigos. Jugamos en los columpios y toboganes, pateamos una pelota y hacemos picnic. Es divertido correr y sentir el sol en nuestra cara.","2"));
                textItems.add(new TextItem("Mi color favorito", "Mi color favorito es el azul. Es el color del cielo en un día soleado y me hace sentir feliz. Me gusta vestir con ropa azul y decorar mi habitación con cosas azules. ¡El azul es el mejor color!","2"));
                textItems.add(new TextItem("Yendo a la playa", "Me encanta ir a la playa. La arena es cálida y suave, y el agua es refrescante. Me gusta construir castillos de arena, recolectar conchas y nadar en las olas. ¡Empaquemos nuestras cosas y vayamos a la playa!","2"));
                textItems.add(new TextItem("Un día en la escuela", "Voy a la escuela todos los días. Aprendo matemáticas, lectura, ciencias y más. Tengo recreo con mis amigos y almuerzo en la cafetería. La escuela puede ser desafiante, pero también es divertido. ¡Disfruto aprendiendo cosas nuevas!","2"));
                textItems.add(new TextItem("Jugando con mi perro", "Tengo un perro llamado Max. Es peludo y amigable, y jugamos juntos todos los días. Nos gusta dar paseos, perseguirnos en el patio y acurrucarnos en el sofá. ¡Max es mi mejor amigo!","2"));
                textItems.add(new TextItem("Mi familia", "Tengo una mamá, papá y hermanito. Nos encanta pasar tiempo juntos, ya sea jugando juegos, viendo películas o yendo de aventuras. Mi familia es lo mejor, y estoy agradecido por ellos todos los días.","2"));
                textItems.add(new TextItem("En el jardín", "Me gusta hacer jardinería con mi mamá. Plantamos flores, verduras y hierbas. Las regamos y las vemos crecer. Es divertido ver los resultados de nuestro trabajo duro y disfrutar de la belleza de la naturaleza.","2"));
                textItems.add(new TextItem("Comiendo pizza", "La pizza es mi comida favorita. Me gusta con pepperoni, champiñones y mucho queso. Es crujiente, quesosa y deliciosa. ¡Podría comer pizza todos los días!","2"));
                textItems.add(new TextItem("En el autobús", "Tomo el autobús a la escuela todos los días. Me gusta sentarme junto a la ventana y ver el mundo pasar. A veces leo un libro o escucho música. Viajar en el autobús es parte de mi rutina diaria.","2"));
            }
            ListView textList = findViewById(R.id.list_text );
            textList.setAdapter(new TextItemAdapter(this,textItems));
        }
    }
}