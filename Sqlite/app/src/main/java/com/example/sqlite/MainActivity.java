package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editTextNome, editTextEmail, editTextDataNasc;
    Button button;
    ListView ListView;
    ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Associar variavis locais a views da interface
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDataNasc = findViewById(R.id.editTextDataNasc);
        button = findViewById(R.id.button);
        ListView = findViewById(R.id.ListView);
        ids = new ArrayList<>();

        //defininfo tratamnto para evento e click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nome", nome);
                cv.put("email", email);

                long status = database.insert("pessoas", null, cv);
                if (status>0){
                    Toast.makeText(getApplicationContext(), "Deu boa", Toast.LENGTH_LONG).show();
                    limparCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "Deu ruim", Toast.LENGTH_LONG).show();
                }
                carregarListagem();
            }
        });

        database = openOrCreateDatabase("meubd", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar, datanasc Date)");
        carregarListagem();
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemId = ids.get(position);
                carregarDados(itemId);
            }
        });
    }

    public void carregarListagem(){
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas ", null);
        cursor.moveToFirst();
        ArrayList<String> nomes = new ArrayList<>();
        ids.clear();

        while (!cursor.isAfterLast()){
            nomes.add(cursor.getString(1));
            ids.add(cursor.getInt(0));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nomes);
        ListView.setAdapter(adapter);
    }

    private void carregarDados(int id) {
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            editTextNome.setText(cursor.getString(1)); // Nome
            editTextEmail.setText(cursor.getString(2)); // Email
        }
        cursor.close();
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextDataNasc.setText("");
    }
}