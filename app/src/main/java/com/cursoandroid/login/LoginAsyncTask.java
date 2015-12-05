package com.cursoandroid.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by boroni on 20/11/15.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, String> {
    ImageView imageView =  null;
    private Context ctx;
    private ProgressDialog dialog;


    public LoginAsyncTask(Context ctx){
        this.ctx = ctx;
        dialog = new ProgressDialog(ctx);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (dialog != null){
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... url_json) {

        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_json[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //  Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return  (total.toString());

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (Exception ex){
            ex.printStackTrace();


            Log.i("error", "" + conn.getErrorStream());
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return  "";
    }

    @Override
    protected void onPostExecute(String result) {
      if (dialog != null){
          dialog.hide();
      }
        if (parseJson(result)){
            Intent i = new Intent(ctx,ListaActivity.class);
            ctx.startActivity(i);
        }else{
            Toast.makeText(ctx,"Login Incorreto",Toast.LENGTH_LONG).show();
        }
    }

    private Boolean parseJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.optInt("id");
//                String nome = jsonObject.optString("nome").toString();
//                String telefone = jsonObject.optString("telefone").toString();
                return true;

            }

        } catch (JSONException e) {}

        return false;
    }



}