package com.example.harrypotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.harrypotter.harryapi.harryapiservice;
import com.example.harrypotter.model.harry;
import com.example.harrypotter.model.harryrespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Harry";
    private RecyclerView recyclerView;
    private listaharryadapter Listaharryadapter;
    private Retrofit retrofit;

    private int offset;

    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Listaharryadapter = new listaharryadapter(this);
        recyclerView.setAdapter(Listaharryadapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerdatos(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("https://ffxivcollect.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;
        obtenerdatos(offset);
        Listaharryadapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=recyclerView.getChildAdapterPosition(v);
                obtenerdetalle(j);

            }
        });

    }
    public void obtenerdetalle(int cont){
        harryapiservice service = retrofit.create(harryapiservice.class);
        Call<harryrespuesta> Harryrespuestacall = service.obtenerlistaharry();

        Harryrespuestacall.enqueue(new Callback<harryrespuesta>() {
            @Override
            public void onResponse(Call<harryrespuesta> call, Response<harryrespuesta> response) {
                if (response.isSuccessful()) {
                    harryrespuesta Harryrespuesta = response.body();
                    ArrayList<harry> listaHarry = Harryrespuesta.getResults();


                    int num= listaHarry.size();
                    for (int i = 0; i < num; i++) {
                        if(cont==i) {
                            harry h = listaHarry.get(i);
                            String p = h.getName();
                            String link=h.getImage();
                            String desc=h.getDescription();
                            Intent intent=new Intent(MainActivity.this,details.class);
                            intent.putExtra("dato1",p);
                            intent.putExtra("dato2",link);
                            intent.putExtra("dato3",desc);
                            intent.putExtra("dato4",h.getMovement());
                            intent.putExtra("dato5",h.getId());
                            startActivity(intent);



                            Toast.makeText(MainActivity.this, "" + p, Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }


            }

            @Override
            public void onFailure(Call<harryrespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });



    }


    private void obtenerdatos(int offset){
        harryapiservice service = retrofit.create(harryapiservice.class);
        Call<harryrespuesta> Harryrespuestacall = service.obtenerlistaharry();

        Harryrespuestacall.enqueue(new Callback<harryrespuesta>() {
            @Override
            public void onResponse(Call<harryrespuesta> call, Response<harryrespuesta> response) {
                if (response.isSuccessful()) {
                    harryrespuesta Harryrespuesta = response.body();
                    ArrayList<harry> listaHarry = Harryrespuesta.getResults();

                    /*
                    int num= listaHarry.size();
                    for (int i = 0; i < num; i++) {
                        harry h = listaHarry.get(i);
                        String p = h.getName();
                        Log.i(TAG, "Harry: " + p);

                    }
                    */
                    Listaharryadapter.adicionarListaharry(listaHarry);


                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }


            }

            @Override
            public void onFailure(Call<harryrespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });



    }

    public void salirmain(View view){

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();


    }

}