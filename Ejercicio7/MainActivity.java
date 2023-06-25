package com.example.ejercicio7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText buscar;
    EditText copiar;
    ScrollView textViewWrapper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copiar = findViewById(R.id.paste);
        buscar = findViewById(R.id.search);
        textViewWrapper = findViewById(R.id.textViewWrapper);
        button = findViewById(R.id.button);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String criteria = buscar.getText().toString();
                String fullText = copiar.getText().toString();
                if (fullText.contains(criteria)) {
                    int indexOfCriteria = fullText.indexOf(criteria);
                    int lineNumber = copiar.getLayout().getLineForOffset(indexOfCriteria);
                    String highlighted = "<font color='red'>"+criteria+"</font>";
                    fullText = fullText.replace(criteria, highlighted);
                    copiar.setText(Html.fromHtml(fullText));

                    textViewWrapper.scrollTo(0, copiar.getLayout().getLineTop(lineNumber));
                }
            }
        });
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    String fullText = copiar.getText().toString();
                    fullText = fullText.replace("<font color='red'>", "");
                    fullText = fullText.replace("</font>", "");
                    copiar.setText(fullText);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}