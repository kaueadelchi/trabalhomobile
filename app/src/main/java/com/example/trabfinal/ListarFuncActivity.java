package com.example.trabfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarFuncActivity extends AppCompatActivity {

    private ListView listView;
    private FuncDAO dao;
    private List<Func> funcs;
    private List<Func> funcsFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_func);

        listView = findViewById(R.id.listafuncs);
        dao = new FuncDAO(this);
        funcs = dao.obterTodos();
        funcsFiltrados.addAll(funcs);
        FuncAdapter adaptador = new FuncAdapter(this, funcsFiltrados);
        //ArrayAdapter<Atleta> adaptador = new ArrayAdapter<Atleta>(this, android.R.layout.simple_list_item_1, atletasFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu (Menu menuf) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_func, menuf);

        SearchView sv = (SearchView) menuf.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraFunc(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu (ContextMenu menuf, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menuf, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_func, menuf);
    }

    public void procuraFunc(String nome){
        funcsFiltrados.clear();
        for(Func f : funcs) {
            if(f.getNome().toLowerCase().contains(nome.toLowerCase())) {
                funcsFiltrados.add(f);
            }
        }
        listView.invalidateViews();
    }

    public void excluirFunc(MenuItem itemf) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) itemf.getMenuInfo();
        final Func funcsExcluir = funcsFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certeza que deseja excluir o atleta?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        funcsFiltrados.remove(funcsExcluir);
                        funcs.remove(funcsExcluir);
                        dao.excluir(funcsExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    public void cadastrarFunc(MenuItem itemf) {
        Intent it = new Intent(this, MainFuncActivity.class);
        startActivity(it);
    }


    @Override
    public void onResume(){
        super.onResume();
        funcs = dao.obterTodos();
        funcsFiltrados.clear();
        funcsFiltrados.addAll(funcs);
        listView.invalidateViews();
    }

}
