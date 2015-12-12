package com.cursoandroid.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.library.Mensagem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    ListView listView;

    //constant
    private static final int PICK_Camera_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Mensagem().enviarMensagem("Teste",this);

        listView = (ListView) findViewById(R.id.lista);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"Ligar", "Web", "Camera"});

        List<String> itens = new ArrayList<String>();
        itens.add("Ligar");
        itens.add("Web");
        itens.add("Camera");
        itens.add("Download Activity");

      CustomAdapter customAdapter = new CustomAdapter(this,R.layout.custom_item,itens);

        List<Integer> imagens = new ArrayList<Integer>();
        imagens.add(R.drawable.chek);
        imagens.add(R.drawable.ic_launcher);
        imagens.add(R.drawable.chek);
        imagens.add(R.drawable.ic_launcher);

        customAdapter.setCtx(this);

        customAdapter.setImagens(imagens);
        // Assign adapter to ListView
//        listView.setAdapter(adapter);
        listView.setAdapter(customAdapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  item value : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                switch (itemPosition) {
                    case 0:
                        call("79900000000");
                        break;
                    case 1:
                        openURL("http://www.google.com");
                        break;
                    case 2:
                        pickImage();
                        break;
                    case 3:
                        openDownloadImage();
                        break;

                }

            }

        });
    }

    private void openDownloadImage(){
        Intent i = new Intent(this,DownloadActivity.class);
        startActivity(i);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        String imagePath = null;
        FileInputStream in;
        File destination = new File(Environment
                .getExternalStorageDirectory(), "foto" + ".jpg");
        switch (requestCode) {
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {

                    try {
                        in = new FileInputStream(destination);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    imagePath = destination.getAbsolutePath();

                     Toast.makeText(this, "" + imagePath +
                     "",Toast.LENGTH_LONG).show();

                    break;

                }

        }
    }

    private void call(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    private void openURL(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void pickImage(){
        File destination = new File(Environment
                .getExternalStorageDirectory(), "foto" + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(destination));
        startActivityForResult(intent, PICK_Camera_IMAGE);


    }


}
