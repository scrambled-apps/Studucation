package com.studucation.readatweet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) { //<-- oncreate: wanneer de activity wordt aangemaakt
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash); //<-- hier wordt layout file activity_splash gebruikt


        // hier gaan we afhandelen wat er gebeurt als je op de afbeelding klikt
        final Context a = this;
        ImageView ivron = (ImageView) findViewById(R.id.iv_splash_ron); //koppelen view object "ivron" aan view uit layout

        //hieronder staat wat er gebeurt als je op het plaatje klikt
        ivron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String showthistext = "Good morning San Diego!"; //de weer te geven text

                //hieronder een Toast: dit is een simpele manier om dingen aan de gebruiker te laten zien
                Toast.makeText(getApplicationContext(),
                        showthistext
                        ,Toast.LENGTH_SHORT).show();

                // door naar nieuwe intent.
//                Intent i = new Intent(Splash.this, MainActivity.class);
//                a.startActivity(i);


            }
        });

    }
    ////////////////////
    //ALLES HIERONDER NEGEREN////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
