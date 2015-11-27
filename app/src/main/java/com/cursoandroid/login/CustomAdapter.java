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


        TextView nome = (TextView) v.findViewById(R.id.nome);
        nome.setText(getItem(position));

        ImageView imagem  = (ImageView) v.findViewById(R.id.imageView);
//        imagem.setImageResource(imagens.get(position));
        new DownloadImageAsync("http://10.27.168.168/teste/softeam.jpg").execute(imagem);

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