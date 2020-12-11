package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainFuncActivity extends AppCompatActivity {

    private EditText nome;
    private EditText idade;
    private FuncDAO dao;
    private Func func = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_func);

        nome = findViewById(R.id.editNomef);
        idade = findViewById(R.id.editIdadef);
        dao = new FuncDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("func")){
            func = (Func) it.getSerializableExtra("func");
            nome.setText(func.getNome());
            idade.setText(func.getIdade());
        }
    }

    public void cadastrarFunc(View view) {

        if (func == null) {
            func = new Func();
            func.setNome(nome.getText().toString());
            func.setIdade(idade.getText().toString());
            long id = dao.inserir(func);
            Toast.makeText(this, "Funcionario inserido com id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            func.setNome(nome.getText().toString());
            func.setIdade(idade.getText().toString());
            dao.atualizar(func);
            Toast.makeText(this, "Funcionario foi atualizado ", Toast.LENGTH_SHORT).show();
        }
    }
}