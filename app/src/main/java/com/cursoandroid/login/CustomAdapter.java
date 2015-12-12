package com.cursoandroid.login;

/**
 * Created by boroni on 13/11/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.DownloadImageAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boroni on 24/09/15.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    private List<Integer> imagens;

    private  Context ctx;

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    public List<Integer> getImagens() {
        return imagens;
    }

    public void setImagens(List<Integer> imagens) {
        this.imagens = imagens;
    }
    public void setCtx(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_item, null);
        }

        List<String> urls = new ArrayList<String>();
        urls.add("http://localhost/teste/softeam.jpg");

        urls.add("http://localhost/teste/softeam.jpg");

        urls.add("http://localhost/teste/softeam.jpg");

        urls.add("http://localhost/teste/softeam.jpg");

//        urls.add("http://localhost/teste/softeam.jpg");
//        urls.add("http://www.canon.pt/Images/Android-logo_tcm121-1232684.png");
//        urls.add("https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg");
//        urls.add("http://www.comillait.com/wp-content/uploads/2015/09/android-pissin-on-apple-decal.jpg");

    

        TextView nome = (TextView) v.findViewById(R.id.nome);
        nome.setText(getItem(position));

        ImageView imagem  = (ImageView) v.findViewById(R.id.imageView);
//        imagem.setImageResource(imagens.get(position));
        new DownloadImageAsync(urls.get(position)).execute(imagem);

        Button btn = (Button) v.findViewById(R.id.informar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,""+position,Toast.LENGTH_LONG).show();
            }
        });





        return v;
    }

}