package com.example.ejercicio6;

import androidx.appcompat.app.AppCompatActivity;

// 0. importar widget segun las variables usadas

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    // 1.declarar variables 0k

    EditText dia;
    EditText mes;
    EditText anio;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // 2.inicializar las variables 0k
        dia = findViewById(R.id.dia);
        dia.setFilters(new InputFilter[]{ new LimitValues("1", "31")}); //aquí se invoca la clase "LimitValues"
        mes = findViewById(R.id.mes);
        mes.setFilters(new InputFilter[]{ new LimitValues("1", "12")});
        anio = findViewById(R.id.anio);
        anio.setFilters(new InputFilter[]{ new LimitValues("1", "3000")});

        resultado = findViewById(R.id.textView);
    }
    // 3. Metodos de los botones Calcular y Limpiar

    public void Calcular (View vista) {

        if (dia.getText().toString().equals("")) {
            Toast.makeText(this, "debes agregar la fecha completa", Toast.LENGTH_SHORT).show();
        } else if (mes.getText().toString().equals("")) {
            Toast.makeText(this, "debes agregar el mes", Toast.LENGTH_SHORT).show();
        } else if (anio.getText().toString().equals("")) {
            Toast.makeText(this, "debes agregar el año", Toast.LENGTH_SHORT).show();
        } else { //aqui
            int day = Integer.parseInt(dia.getText().toString());
            int month = Integer.parseInt(mes.getText().toString());
            int year = Integer.parseInt(anio.getText().toString());

            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day); // debido a que la posicion de los meses van de 0-11 le puse -1 al valor

            DateFormat formatter = new SimpleDateFormat("EEEE");
            String dayOfWeekString = formatter.format(cal.getTime());
            resultado.setText(dayOfWeekString);
        }
    }

    public void Limpiar (View vista){
        dia.setText(""); // Limpia y cambia el texto actual a un " "
        mes.setText("");
        anio.setText("");
        resultado.setText("");
    }

}