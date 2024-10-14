package com.example.listaplanetas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanetaAdapter extends ArrayAdapter<Planeta> {

    Context Contexto;
    Integer Res;
    List<Planeta> ListaPlaneta;

    public PlanetaAdapter(@NonNull Context context, int resource, @NonNull List<Planeta> objects) {
        super(context, resource, objects);
        Contexto=context;
        Res=resource;
        ListaPlaneta=objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(Contexto);
        View v = layoutInflater.inflate(Res,parent,false);

        TextView tvnomeplaneta = v.findViewById(R.id.lblnome);
        ImageView imageview = v.findViewById(R.id.imageview);

        Planeta planeta = ListaPlaneta.get(position);
        tvnomeplaneta.setText(planeta.nome);
        imageview.setImageResource(planeta.foto);

        return v;
    }
}
