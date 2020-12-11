package com.example.trabfinal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FuncAdapter extends BaseAdapter {

    private List<Func> funcList;
    private Activity activity;

    public FuncAdapter(Activity activity, List<Func> funcList) {
        this.activity = activity;
        this.funcList = funcList;
    }

    @Override
    public int getCount() {
        return funcList.size();
    }

    @Override
    public Object getItem(int i) {
        return funcList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return funcList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item_func, viewGroup, false);
        TextView nome = v.findViewById(R.id.txt_func_nome);
        TextView idade = v.findViewById(R.id.txt_func_idade);
        Func f = funcList.get(i);
        nome.setText(f.getNome());
        idade.setText(f.getIdade());

        return v;
    }
}

