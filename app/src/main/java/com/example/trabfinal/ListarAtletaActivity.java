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

import java.util.ArrayList;
import java.util.List;

public class ListarAtletaActivity extends AppCompatActivity {

    private ListView listView;
    private AtletaDAO dao;
    private List<Atleta> atletas;
    private List<Atleta> atletasFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_atleta);

        listView = findViewById(R.id.listaatletas);
        dao = new AtletaDAO(this);
        atletas = dao.obterTodos();
        atletasFiltrados.addAll(atletas);
        AtletaAdapter adaptador = new AtletaAdapter(this, atletasFiltrados);
        //ArrayAdapter<Atleta> adaptador = new ArrayAdapter<Atleta>(this, android.R.layout.simple_list_item_1, atletasFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraAtleta(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraAtleta(String nome){
        atletasFiltrados.clear();
        for(Atleta a : atletas) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                atletasFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Atleta atletaExcluir = atletasFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certeza que deseja excluir o atleta?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        atletasFiltrados.remove(atletaExcluir);
                        atletas.remove(atletaExcluir);
                        dao.excluir(atletaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
            dialog.show();

    }

    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Atleta atletaAtualizar = atletasFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("atleta", atletaAtualizar);
        startActivity(it);

    }

    @Override
    public void onResume(){
        super.onResume();
        atletas = dao.obterTodos();
        atletasFiltrados.clear();
        atletasFiltrados.addAll(atletas);
        listView.invalidateViews();
    }
}