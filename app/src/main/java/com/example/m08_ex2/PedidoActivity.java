package com.example.m08_ex2;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PedidoActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir);

        Intent intent = getIntent();
        String resumen = intent.getStringExtra("RESUMEN");

        TextView textView_pedido = findViewById(R.id.textView_pedido);
        textView_pedido.setText(resumen);
    }


}
