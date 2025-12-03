package com.example.zadaniezaliczeniowe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
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

import java.util.Random;

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

        buttonZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generujHaslo();
            }
        });

        buttonZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zatwierdzDane();
            }
        });

    }

    void generujHaslo(){
        String maleLitery="abcdefghijklmnopqrstuvwxyz";
        String duzeLitery="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cyfry = "01234566789";
        String znakiSpecjalne="!@#$%^&*()_-+=";

        StringBuilder haslo = new StringBuilder();
        Random random=new Random();

        int dlugosc;
        try{
            dlugosc = Integer.parseInt(editDlugoscHasla.getText().toString());
        }catch (NumberFormatException exeption){
            dlugosc=8;
        }

        for(int i = 0; i<dlugosc; i++){
            haslo.append(maleLitery.charAt(random.nextInt(maleLitery.length())));
        }

        if(checkLitery.isChecked()&& dlugosc>=1){
            haslo.setCharAt(0, duzeLitery.charAt(random.nextInt(duzeLitery.length())));
        }
        if(checkCyfry.isChecked()&& dlugosc>=2){
            haslo.setCharAt(1, cyfry.charAt(random.nextInt(cyfry.length())));
        }
        if(checkZnaki.isChecked()&& dlugosc>=3){
            haslo.setCharAt(2, znakiSpecjalne.charAt(random.nextInt(znakiSpecjalne.length())));
        }

        wygenerowaneHaslo = haslo.toString();

        new AlertDialog.Builder(this).setTitle("Wygenerowane hasło").setMessage(wygenerowaneHaslo).setPositiveButton("ok",null).show();
    }
    void zatwierdzDane(){
        String imie = editImie.getText().toString();
        String nazwisko = editNazwisko.getText().toString();
        String stanowisko = spinnerStanowisko.getSelectedItem().toString();

        String wiadomosc = "Dane:\n"+"Imie:\t"+imie+"\n"+"Nazwisko:\t"+nazwisko+"\n"+"Stanowisko:\t"+stanowisko+"\n"+"Hasło:\t"+wygenerowaneHaslo+"\n";

        new AlertDialog.Builder(this).setTitle("Potwierdzenie danych").setMessage(wiadomosc).setPositiveButton("ok",null).show();
    }
}