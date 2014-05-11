package com.studucation.readatweet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class ImageLoader extends AsyncTask<Void, Void, Boolean >{


    Drawable d;
	private ImageView iv;
	private String imgurl;
	private int maxWidth;
	private int maxHeight;
	private UUID uniqueid;

    /**
     * initialiseer deze task met de url die ingeladen moet worden en de imageview waar de afbeelding komt en voer uit
     * @param imgstring
     * @param iview
     */
	public ImageLoader(String imgstring,ImageView iview){

		this.imgurl = imgstring;
		this.iv = iview;
		maxWidth = -1 ;//no val
		maxHeight = -1;


	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		uniqueid = UUID.randomUUID();
		iv.setTag(uniqueid);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		boolean succes = false;


            d = LoadImageFromWebOperations(imgurl);
			if(d!=null){
				succes = true;
			}
	return succes;
	}



	@Override
	protected void onPostExecute(Boolean succes) {
		// TODO Auto-generated method stub
		super.onPostExecute(succes);

		if(succes && iv.getTag().equals(uniqueid)){

			iv.setAdjustViewBounds(true);
			if(maxHeight>0){
				iv.setMaxHeight(maxHeight);
			}
			if(maxWidth>0){
				iv.setMaxWidth(maxWidth);
			}

			iv.setImageDrawable(d);


		}

	}

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

	public void SetMaxSize(int width, int height){
		this.maxWidth = width;
		this.maxHeight = height;
	}

	/**
	 * Makes a bitmap square and centered. 
	 * @param srcBmp
	 * @return
	 */
	public Bitmap getCenteredImage(Bitmap srcBmp){
		Bitmap dstBmp;
		if (srcBmp.getWidth() >= srcBmp.getHeight()){

			dstBmp = Bitmap.createBitmap(
					srcBmp, 
					srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
					0,
					srcBmp.getHeight(), 
					srcBmp.getHeight()
					);

		}else{

			dstBmp = Bitmap.createBitmap(
					srcBmp,
					0, 
					srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
					srcBmp.getWidth(),
					srcBmp.getWidth() 
					);
		}
		return dstBmp;

	}
	
}