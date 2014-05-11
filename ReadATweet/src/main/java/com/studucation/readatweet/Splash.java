package com.studucation.readatweet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends Activity {

    //Dit stukje code wordt gerund bij het opstarten van deze activity
    @Override
    protected void onCreate(Bundle savedInstanceState) { //<-- oncreate: wanneer de activity wordt aangemaakt
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); //<-- hier wordt layout file activity_splash aangemaakt
        final Context a = this;
        //nu gaan we zorgen dat er iets gebeurd als we op ron klikken
        ImageView ivron = (ImageView) findViewById(R.id.iv_splash_ron);
        ivron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.hello_world),Toast.LENGTH_SHORT).show();

                // door naar nieuwe intent.
//                Intent i = new Intent(Splash.this, MainActivity.class);
//                a.startActivity(i);
//

            }
        });

    }


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
