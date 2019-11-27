package com.example.m08_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String resumen;

    int contador=0;
    int maxCafe=10;

    boolean whipped =false;
    boolean chocolate = false;

    float total=0;

//    Intent i = new Intent(this, PedidoActivity.class );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_aumentar = findViewById(R.id.button_mas);
        Button button_disminuir = findViewById(R.id.button_menos);
        Button button_order = findViewById(R.id.button_order);
        Button button_enviar = findViewById(R.id.button_pedir_enviar);

        final CheckBox checkBox_Whipped = findViewById(R.id.checkBox);
        final CheckBox checkBox_Chocolate = findViewById(R.id.checkBox2);

        final TextView quantity = findViewById(R.id.contador);

        button_aumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aumentar(v);
                quantity.setText(String.valueOf(contador));
            }
        });

        button_disminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disminuir(v);
                quantity.setText(String.valueOf(contador));
            }
        });

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedir(v, checkBox_Chocolate, checkBox_Whipped);
                actualizaTotal(contador, quantity);
                pedirMasResumen(v);
            }
        });

        button_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedir(v, checkBox_Chocolate, checkBox_Whipped);
                actualizaTotal(contador, quantity);
                pedirMasEnviar(v);
            }
        });


    }

    /* Si la cantidad de cafes es menor a 10:
       - Aumenta en 1 la cantidad y la vista de la cantidad
       - Invoca el metodo que actualiza el precio Total
      Si no:
       - Invoca al metodo para lanzar alerta avisando de la cantidad maxima */
    public void aumentar(View view) {
        if(contador<maxCafe){
            contador++;
        }
    }

    /* Si la cantidad de cafes es mayor a 1:
       - Disminuye en 1 la cantidad y la vista de la cantidad
       - Invoca el metodo que actualiza el precio Total
      Si no:
       - Invoca al metodo para lanzar alerta avisando de la cantidad minima */
    public void disminuir(View view) {
        if(contador>0){
            contador--;
        }
    }

    /*  Recopila la información del pedido y añade
       un resumen en el TextView vacío de la interfaz. */

    public void pedir(View view, CheckBox checkBox_Chocolate, CheckBox checkBox_Whipped) {

        if(checkBox_Whipped.isChecked()){
            whipped =true;
        }

        if(checkBox_Chocolate.isChecked()){
            chocolate =true;
        }

    }

    /* Lanza un toast de corta duracion
      mensaje: contenido del toast */
    public void lanzaToast(String mensaje) {

    }

    /* Calcula el precio total del pedido teniendo en cuenta
       - si se ha seleccionado nata y chocolate
       - muestra el resultado con 2 decimales */
    public void actualizaTotal(float contador, TextView quantity) {
        total=contador*5;

        if(whipped){
            total+=0.7f;
        }
        if(chocolate){
            total+=0.5f;

        }
        whipped=false; chocolate=false;

        EditText editText = findViewById(R.id.editText);
        TextView summary = findViewById(R.id.text_total);

        summary.setText(editText.getText() + "\n" + "Add whipped cream? " + whipped + "\n" + "Add chocolate? " + chocolate + "\n" + "Quantity " + quantity.getText() + "\n" +  "Total $" + total);
        resumen = summary.getText().toString();

    }

    public void pedirMasResumen(View view) {
        Intent intent = new Intent(this, PedidoActivity.class);
        intent.putExtra("RESUMEN", resumen);
        startActivity(intent);
    }

    public void pedirMasEnviar(View view){
        String asunto = "Just Java Coffe";

        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:")); // Sólo apps de correo
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "justjava@coffe.shop" });
        i.putExtra(Intent.EXTRA_SUBJECT, asunto);
        i.putExtra(Intent.EXTRA_TEXT, resumen);

        // Sólo se lanzará el activity si hay apps disponibles en el sistema para ejecutarla.
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }


    }

}
