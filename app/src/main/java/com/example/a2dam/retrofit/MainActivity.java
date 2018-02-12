package com.example.a2dam.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2dam.retrofit.api.SwapiClient;
import com.example.a2dam.retrofit.api.SwapiService;
import com.example.a2dam.retrofit.api.responses.PeopleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SwapiService swapiService;
    private Button btnPersona;
    private TextView lblPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swapiService = SwapiClient.getInstance().getService();
        lblPersona = findViewById(R.id.lblPersona);
        btnPersona = findViewById(R.id.btnPersona);
        btnPersona.setOnClickListener(v -> obtenerPersona());
    }

    //Se obtiene la persona llamando al metodo get person del servicio, obteniendo un call
    // se hace una cola con un nuevo call y se obtiene el body de la respuesta comprobando primero que existe
    private void obtenerPersona() {
        Call<PeopleResponse> llamada = swapiService.getPerson();
        llamada.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                if (response != null && response.isSuccessful()) {
                    PeopleResponse peopleResponse = response.body();
                    lblPersona.setText(peopleResponse.getName());
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {

            }
        });


    }

}
