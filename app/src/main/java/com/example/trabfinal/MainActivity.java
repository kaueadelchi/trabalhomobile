package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText esporte;
    private EditText idade;
    private AtletaDAO dao;
    private Atleta atleta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        esporte = findViewById(R.id.editEsporte);
        idade = findViewById(R.id.editIdade);
        dao = new AtletaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("atleta")){
            atleta = (Atleta) it.getSerializableExtra("atleta");
            nome.setText(atleta.getNome());
            esporte.setText(atleta.getEsporte());
            idade.setText(atleta.getIdade());
        }
    }

    public void cadastrar(View view) {

        if (atleta == null) {
            atleta = new Atleta();
            atleta.setNome(nome.getText().toString());
            atleta.setEsporte(esporte.getText().toString());
            atleta.setIdade(idade.getText().toString());
            long id = dao.inserir(atleta);
            Toast.makeText(this, "Atleta inserido com id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            atleta.setNome(nome.getText().toString());
            atleta.setEsporte(esporte.getText().toString());
            atleta.setIdade(idade.getText().toString());
            dao.atualizar(atleta);
            Toast.makeText(this, "Atleta foi atualizado ", Toast.LENGTH_SHORT).show();
        }
    }
}