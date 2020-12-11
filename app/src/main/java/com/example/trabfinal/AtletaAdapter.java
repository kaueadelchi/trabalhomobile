package com.example.trabfinal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AtletaAdapter extends BaseAdapter {

    private List<Atleta> atletaList;
    private Activity activity;

    public AtletaAdapter(Activity activity,List<Atleta> atletaList) {
        this.activity = activity;
        this.atletaList = atletaList;
    }

    @Override
    public int getCount() {
        return atletaList.size();
    }

    @Override
    public Object getItem(int i) {
        return atletaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return atletaList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nome = v.findViewById(R.id.txt_nome);
        TextView esporte = v.findViewById(R.id.txt_esporte);
        TextView idade = v.findViewById(R.id.txt_idade);
        Atleta a = atletaList.get(i);
        nome.setText(a.getNome());
        esporte.setText(a.getEsporte());
        idade.setText(a.getIdade());

        return v;
    }
}
