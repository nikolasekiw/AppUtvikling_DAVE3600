package com.example.s333975s331153mappe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class EndreRom extends AppCompatActivity {
    EditText romNr, kapasitet, beskrivelse;
    Toolbar tb;
    int innRomID, innRomNr, innKapasitet;
    String innBeskrivelse;

    public static final Pattern KAPASITET = Pattern.compile("[0-9]{1,3}");
    public static final Pattern ROMNR = Pattern.compile("[0-9]{1,3}");
    public static final Pattern BESKRIVELSE = Pattern.compile("[a-zæøåA-ZÆØÅ0-9 ]{2,40}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endrerom);
        romNr = findViewById(R.id.innRomNr);
        kapasitet = findViewById(R.id.innKapasitet);
        beskrivelse = findViewById(R.id.innBeskrivelseRom);

        tb = findViewById(R.id.toolbarRom);
        tb.setTitle("\tOsloMet-Booking");
        tb.setLogo(R.mipmap.ic_launcher_round);
        tb.inflateMenu(R.menu.manu_rom);
        setActionBar(tb);

        tb.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EndreRom.this, HusOversikt.class);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        innRomID = intent.getIntExtra("romID", 0);
        innRomNr = intent.getIntExtra("romNr", 0);
        innBeskrivelse = intent.getStringExtra("beskrivelse");
        innKapasitet = intent.getIntExtra("kapasitet", 0);

        Log.d("innRomID", Integer.toString(innRomID));
        romNr.setText(Integer.toString(innRomNr));
        beskrivelse.setText(innBeskrivelse);
        kapasitet.setText(Integer.toString(innKapasitet));
    }

    public void lagreRom(View v){
        SendJSON task = new SendJSON();
        if(!validerRomnr() | !validerKapasitet() | !validerBeskrivelse()) {
            Toast.makeText(EndreRom.this, "Alle felt må være riktig fylt inn riktig", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String urlString = ("http://student.cs.hioa.no/~s331153/endreromjson.php/?" +
                    "RomID=" + innRomID +
                    "&RomNr=" + romNr.getText().toString() +
                    "&Kapasitet=" + kapasitet.getText().toString() +
                    "&Beskrivelse=" + beskrivelse.getText().toString()).replaceAll(" ", "%20");
            task.execute(urlString);
            Toast.makeText(this, "Rom endret!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,
                    HusOversikt.class);
            startActivity(intent);
        }
    }

    //----- Metoder for validering -----//
    public boolean validerRomnr(){
        String romNrInput = romNr.getText().toString().trim();
        if(romNrInput.isEmpty()){
            romNr.setError("Romnr kan ikke være tomt");
            return false;
        } else if (!ROMNR.matcher(romNrInput).matches()){
            romNr.setError("Romnr må bestå av tall mellom 1 og 999");
            return false;
        } else {
            romNr.setError(null);
            return true;
        }
    }

    public boolean validerKapasitet(){
        String kapasitetInput = kapasitet.getText().toString().trim();
        if(kapasitetInput.isEmpty()){
            kapasitet.setError("Kapasitet kan ikke være tomt");
            return false;
        } else if (!KAPASITET.matcher(kapasitetInput).matches()){
            kapasitet.setError("Kapasitet må bestå av tall mellom 1 og 999");
            return false;
        } else {
            kapasitet.setError(null);
            return true;
        }
    }

    public boolean validerBeskrivelse(){
        String beskrivelseInput = beskrivelse.getText().toString().trim();
        if(beskrivelseInput.isEmpty()){
            beskrivelse.setError("Beskrivelse kan ikke være tomt");
            return false;
        } else if (!BESKRIVELSE.matcher(beskrivelseInput).matches()){
            beskrivelse.setError("Beskrivelse må bestå av mellom 2 og 40 tegn");
            return false;
        } else {
            beskrivelse.setError(null);
            return true;
        }
    }
}
