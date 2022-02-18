package com.example.newfilemanager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtFile;
    Button btnInserisci,btnLeggi,btnLeg;
    Gestore ge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFile=(TextView) findViewById(R.id.txtFile);
        btnInserisci=(Button)findViewById(R.id.btnInserisci);
        btnLeggi=(Button) findViewById(R.id.btnLeggi);
        btnLeg=(Button) findViewById(R.id.btnRaw);


        ge = new Gestore();
        btnLeggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String righeLette = ge.leggiFile("prova.txt",getApplicationContext());
                Toast.makeText(getApplicationContext(),righeLette,Toast.LENGTH_LONG).show();
            }
        });

        btnInserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String esito = ge.scriviFile("prova.txt",getApplicationContext());
                Toast.makeText(getApplicationContext(),esito,Toast.LENGTH_LONG).show();
            }
        });
        btnLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testo = ge.leggiRawFile(getApplicationContext());
                Toast.makeText(getApplicationContext(),testo,Toast.LENGTH_LONG).show();
            }
        });

    }

}