package com.cursoandroid.aula6;

/**
 * Created by boroni on 13/11/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by boroni on 24/09/15.
 */
public class CustomAdapter extends ArrayAdapter<Tipo> {


    private  Context ctx;

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomAdapter(Context context, int resource, List<Tipo> items) {
        super(context, resource, items);
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
        nome.setText(getItem(position).getNome());


        return v;
    }

}