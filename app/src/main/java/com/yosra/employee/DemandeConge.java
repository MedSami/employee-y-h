package com.yosra.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yosra.employee.API.ApiRequest;
import com.yosra.employee.API.RetrofitServer;
import com.yosra.employee.Model.ResponseDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemandeConge extends AppCompatActivity {
    EditText edtNbJours,edtDate;
    Button btnEnvoyer;
    String idEmploye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_conge);

        edtNbJours=findViewById(R.id.edtNombreJours);
        edtDate=findViewById(R.id.edtDate);
        btnEnvoyer=findViewById(R.id.btnEnvoyer);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            idEmploye = data.getString("idEmploye");

        }

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNbJours.getText().toString().equals("")){
                    Toast.makeText(DemandeConge.this, "Saisir Nb Jours SVP", Toast.LENGTH_SHORT).show();
                }else if (edtDate.getText().toString().equals("")){
                    Toast.makeText(DemandeConge.this, "Saisir Date debut SVP", Toast.LENGTH_SHORT).show();
                }else {
                    ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
                    Call<ResponseDataModel> demandeConge=api.DemandeConge(edtNbJours.getText().toString(),
                            edtDate.getText().toString(),idEmploye);
demandeConge.enqueue(new Callback<ResponseDataModel>() {
    @Override
    public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
        Toast.makeText(DemandeConge.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<ResponseDataModel> call, Throwable t) {
        Toast.makeText(DemandeConge.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
    }
});

                }
            }
        });

    }
}
