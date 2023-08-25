package com.example.harrypotter.harryapi;



import com.example.harrypotter.model.harryrespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface harryapiservice {

    @GET("api/mounts")
    Call<harryrespuesta> obtenerlistaharry();
}
