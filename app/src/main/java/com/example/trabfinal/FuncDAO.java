package com.example.trabfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FuncDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public FuncDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Func func){
        ContentValues values = new ContentValues();
        values.put("nome", func.getNome());
        values.put("idade", func.getIdade());
        return banco.insert("func",null, values);
    }

    public List<Func> obterTodos() {
        List<Func> funcs = new ArrayList<>();
        Cursor cursor = banco.query("func", new String[]{"id", "nome", "idade"},
                null, null,null, null, null);
        while(cursor.moveToNext()) {
            Func f = new Func();
            f.setId(cursor.getInt(0));
            f.setNome(cursor.getString(1));
            f.setIdade(cursor.getString(2));
            funcs.add(f);
        }
        return funcs;
    }

    public void excluir(Func f) {
        banco.delete("func", "id = ?", new String[]{f.getId().toString()});
    }

    public void atualizar(Func func) {
        ContentValues values = new ContentValues();
        values.put("nome", func.getNome());
        values.put("idade", func.getIdade());
        banco.update("func", values, "id = ?", new String[]{func.getId().toString()});
    }

}
