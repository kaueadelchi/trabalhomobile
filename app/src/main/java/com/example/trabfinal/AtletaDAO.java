package com.example.trabfinal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AtletaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AtletaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Atleta atleta){
        ContentValues values = new ContentValues();
        values.put("nome", atleta.getNome());
        values.put("esporte",atleta.getEsporte());
        values.put("idade",atleta.getIdade());
        return banco.insert("atleta",null, values);
    }

    public List<Atleta> obterTodos() {
        List<Atleta> atletas = new ArrayList<>();
        Cursor cursor = banco.query("atleta", new String[]{"id", "nome", "esporte", "idade"},
                null, null,null, null, null);
        while(cursor.moveToNext()) {
            Atleta a = new Atleta();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setEsporte(cursor.getString(2));
            a.setIdade(cursor.getString(3));
            atletas.add(a);
        }
        return atletas;
    }

    public void excluir(Atleta a) {
        banco.delete("atleta", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Atleta atleta) {
        ContentValues values = new ContentValues();
        values.put("nome", atleta.getNome());
        values.put("esporte",atleta.getEsporte());
        values.put("idade",atleta.getIdade());
        banco.update("atleta", values, "id = ?", new String[]{atleta.getId().toString()});
    }

}
