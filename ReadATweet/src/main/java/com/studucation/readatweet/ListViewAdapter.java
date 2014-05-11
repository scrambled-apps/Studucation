package com.studucation.readatweet;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;

        vi = inflater.inflate(R.layout.view_listrow, null); //hier wordt één listrow "opgeblazen", deze gaan we nu vullen

        Tweet tweet = twits.get(i); // één bericht pakken (Tweet object)

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
        // nu gaan we nog even iets fancies met de achtergrondkleur doen
//        int[] colors = new int[3]; //array met kleuren, we vullen het met rood wit blauw
//        colors[0] = R.color.red;
//        colors[1] = R.color.white;
//        colors[2] = R.color.blue;
//
//        //zet als achtergrondkleur een van de wisselende kleurtjes
//        RelativeLayout rl = (RelativeLayout) vi.findViewById(R.id.rl_listbackground);
//        rl.setBackgroundColor(colors[i%colors.length]);


        return vi;
    }


    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}
