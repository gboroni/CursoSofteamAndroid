package com.cursoandroid.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by boroni on 20/11/15.
 */
public class DownloadImageAsync extends AsyncTask<ImageView, Void, Bitmap> {
    ImageView imageView =  null;
    private String url = "";

    public DownloadImageAsync(String url){
        this.url = url;
    }


    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        Bitmap b = downloadImage(url);
        return b;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null && imageView != null)
          imageView.setImageBitmap(result);
    }

    private Bitmap downloadImage(String url) {
        String urldisplay = url;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }


}