package com.yosra.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {
    Button btnDemandeConge,btnEnvoyerCertif;
    String idEmploye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

    btnDemandeConge=findViewById(R.id.btnDemandeConge);
    btnEnvoyerCertif=findViewById(R.id.btnEnvoyerCertif);
        Bundle data = getIntent().getExtras();
        if (data != null) {
            idEmploye = data.getString("idEmploye");

        }
    btnDemandeConge.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(MenuPrincipal.this,DemandeConge.class);
            i.putExtra("idEmploye",idEmploye);
            startActivity(i);
        }
    });

    btnEnvoyerCertif.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(MenuPrincipal.this,EnvoyerCertif.class);
            i.putExtra("idEmploye",idEmploye);
            startActivity(i);
        }
    });

    }
}
