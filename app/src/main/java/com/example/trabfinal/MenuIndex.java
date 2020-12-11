package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_index);
    }

    public void atletasClube(View view) {
        Intent it = new Intent(this, ListarAtletaActivity.class);
        startActivity(it);
    }

    public void funcClube(View view) {
        Intent it = new Intent(this, ListarFuncActivity.class);
        startActivity(it);
    }
}