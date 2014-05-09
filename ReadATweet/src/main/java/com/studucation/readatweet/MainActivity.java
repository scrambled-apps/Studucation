package com.studucation.readatweet;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


public class MainActivity extends Activity {


    final static String myFavTwitterAccount = "RonBurgundy"; //Twitter account naam
    final static String LOG_TAG = "MainActivityLog";
    private ListView listView;

    /**
     * deze functie wordt aangeroepen bij het opstarten van de activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterlist);

        listView = (ListView) findViewById(R.id.listView);






        downloadTweets(myFavTwitterAccount);
    }

    /**
     * Deze functie haalt de twitterfeed op van meegegeven account
     * door gebruik van AsyncTask
     *
     * @param twitterAccount
     */
    public void downloadTweets(String twitterAccount) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadTwitterTask().execute(twitterAccount);
        } else {
            Log.v(LOG_TAG, "Geen internetverbinding beschikbaar.");
        }
    }

    /**
     * ROEP DEZE FUNCTIE NIET AAN! DIT GEBEURT AUTOMATISCH ALS ER GEDOWNLOAD IS
     * Deze functie wordt gerund als het Twitter object is gedownload
     *
     * Hier kun je dus gaan aanpassen wat er gebeurt!
     *
     * @param twits
     */
    public void handleLoadedTweets(Twitter twits) {
        Log.d("LOG_TAG ", twits.toString());

        ListViewAdapter adapter = new ListViewAdapter(this,twits);
        listView.setAdapter(adapter);


    }


    //Hier beneden staat de code die de twitterfeed ophaalt.
    //Negeer dit maar zo veel mogelijk. Belangrijk is even te begrijpen dat de functie
    //handleLoadedTweets wordt gerund als er succesvol is gedownload
    //
    //NOTE: dit is anders dan hoe het normaal (=netjes) gedaan wordt, maar wel een stuk makkelijker te begrijpen
    //Kan je dit allemaal al? Geef dan even een gil, dan maken we het wat anders.
    //
    private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
        private TwitterHelpers twitterHelpers = new TwitterHelpers();

        @Override
        protected String doInBackground(String... screenNames) {
            String result = null;

            if (screenNames.length > 0) {
                result = twitterHelpers.getTwitterStream(screenNames[0]);
            }
            return result;
        }

        // converteert de string om naar een twitter object. Voert daarna de functie handleLoadedTweets uit
        @Override
        protected void onPostExecute(String result) {
            try{
            Log.d("LOG_TAG",result);
            Twitter twits = twitterHelpers.jsonToTwitter(result);
            handleLoadedTweets(twits);
            }catch(NullPointerException npe){
                Log.d(LOG_TAG,"laden mislukt. Klopt de twitter naam?");
            }

        }


    }
}
