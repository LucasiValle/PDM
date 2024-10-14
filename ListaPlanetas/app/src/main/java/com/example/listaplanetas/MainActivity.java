package com.example.listaplanetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listaview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaview = findViewById(R.id.listview);
        PlanetaDAO pdao = new PlanetaDAO();

        PlanetaAdapter padap = new PlanetaAdapter(this,R.layout.itemplaneta,pdao.planetas);
        listaview.setAdapter(padap);

        listaview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this,Integer.toString(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}