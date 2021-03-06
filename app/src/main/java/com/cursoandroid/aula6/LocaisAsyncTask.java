package com.cursoandroid.aula6;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boroni on 20/11/15.
 */
public class LocaisAsyncTask extends AsyncTask<String, Void, String> {

    private Context ctx;
    private ProgressDialog dialog;
    private ListView lista;
    private List<Local> locais;

    public LocaisAsyncTask(Context ctx, ListView lista){
        this.ctx = ctx;
        this.lista = lista;
        dialog = new ProgressDialog(ctx);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (dialog != null){
            this.dialog.setMessage("Carregando...");
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

        parseJson(result);

        CustomAdapterLocais customAdapter = new CustomAdapterLocais(ctx,R.layout.custom_item,locais);
        customAdapter.setCtx(ctx);
        lista.setAdapter(customAdapter);
    }

    private Boolean parseJson(String json){
        locais = new ArrayList<Local>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.optInt("id");
                int tipo = jsonObject.optInt("tipo");
                String nome = jsonObject.optString("nome").toString();
                String telefone = jsonObject.optString("telefone").toString();
                String endereco = jsonObject.optString("endereco").toString();
                String site = jsonObject.optString("site").toString();
                String imagem_url = jsonObject.optString("imagem_url").toString();

                if (Singleton.getInstance().getTipo_clicado().getId() == tipo)
                    locais.add(new Local(id, nome,  telefone,  endereco,  site,  imagem_url,  tipo));

            }

        } catch (JSONException e) {}
        Singleton.getInstance().setLocais(locais);

        return true;
    }



}