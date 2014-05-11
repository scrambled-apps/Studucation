package com.studucation.readatweet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jasper on 09/05/14.
 */
public class ListViewAdapter extends BaseAdapter {

    private Twitter twits;
    private Activity a;
    private static LayoutInflater inflater = null;

    public ListViewAdapter(Activity a, Twitter twits) {
        this.twits = twits;
        this.a = a;
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return twits.size();
    }

    @Override
    public Object getItem(int i) {
        return twits.get(i);
    }

    //ignore
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Hier wordt
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;

        vi = inflater.inflate(R.layout.view_listrow, null); //hier wordt één listrow "opgeblazen", deze gaan we nu vullen

        final Tweet tweet = twits.get(i); // één bericht pakken (Tweet object)

        // title pakken en hier de username in stoppen
        TextView title = (TextView) vi.findViewById(R.id.tv_title);
        title.setText(tweet.getUser().getName());

        //subtitle pakken en hier het bericht in stoppen
        TextView subtitle = (TextView) vi.findViewById(R.id.tv_subtitle);
        subtitle.setText(stripHtml(tweet.getText()));

        //plaatje inladen (dit gaat iets anders dan text, dit gaat Asynchroon om vertragingen tegen te gaan)
        ImageView image = (ImageView) vi.findViewById(R.id.iv_listimage);
        ImageLoader iLoad = new ImageLoader(tweet.getUser().getProfileImageUrl(),image);
        iLoad.execute();

        image.setMinimumWidth(50);
        image.setMinimumHeight(50);

        //nu is alles ingeladen!

/*

        // nu gaan we nog even iets fancies met de achtergrondkleur doen
        int[] colors = new int[3]; //array met kleuren, we vullen het met rood wit blauw
        Resources res = a.getResources();
        colors[0] = res.getColor(R.color.red);
        colors[1] = res.getColor(R.color.white);
        colors[2] = res.getColor(R.color.blue);
//
//        //zet als achtergrondkleur een van de wisselende kleurtjes
        RelativeLayout rl = (RelativeLayout) vi.findViewById(R.id.rl_listbackground);
        rl.setBackgroundColor(colors[i%colors.length]);
*/


        // add button listener
        vi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(a);
                dialog.setContentView(R.layout.dialog_viewtweet);


                String dialogTitle = "Tweet send at "+convertString(tweet.getDateCreated());
                dialog.setTitle(dialogTitle);





                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.tv_detail_subtitle);
                text.setText(stripHtml(tweet.getText()));
                ImageView image = (ImageView) dialog.findViewById(R.id.iv_smallpic);
                ImageLoader iLoad = new ImageLoader(tweet.getUser().getProfileImageUrl(),image);
                iLoad.execute();

               TextView title = (TextView) dialog.findViewById(R.id.tv_detail_title);
                title.setText(tweet.getUser().getName());

                dialog.show();
            }
        });

        return vi;
    }


    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    public String convertString(String input){
        String date = input.substring(0,10);
        date= date + " "+input.substring(input.length()-4,input.length());
        return date;
    }
}
