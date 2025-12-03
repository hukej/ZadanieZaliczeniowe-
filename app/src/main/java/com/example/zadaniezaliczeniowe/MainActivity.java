package com.example.zadaniezaliczeniowe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editImie, editNazwisko, editDlugoscHasla;
    Spinner spinnerStanowisko;
    CheckBox checkLitery, checkCyfry, checkZnaki;
    Button buttonGeneruj, buttonZatwierdz;

    String wygenerowaneHaslo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editImie =findViewById(R.id.editTextImie);
        editNazwisko =findViewById(R.id.editTextNazwisko);
        editDlugoscHasla =findViewById(R.id.editTextDlugoscHasla);
        spinnerStanowisko =findViewById(R.id.spinnerStanowisko);
        checkCyfry =findViewById(R.id.checkBoxCyfry);
        checkLitery =findViewById(R.id.checkBoxLitery);
        checkZnaki =findViewById(R.id.checkBoxZnaki);
        buttonGeneruj =findViewById(R.id.buttonGeneruj);
        buttonZatwierdz =findViewById(R.id.buttonZatwierdz);

        String[] stanowisko = {"Kierownik", "Starszy Programista","Tester", "Mlodszy Programista",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, stanowisko);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStanowisko.setAdapter(adapter);

    }
}