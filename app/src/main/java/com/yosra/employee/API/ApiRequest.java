package com.yosra.employee.API;

import com.yosra.employee.Model.ResponseDataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {


    /******************** Authentification Login*******************/
    @GET("Login.php")
    Call<ResponseDataModel> Login(@Query("identifiant") String identifiant);


    /*************** Inscrire *******************/
    @FormUrlEncoded
    @POST("Inscrire.php")
    Call<ResponseDataModel> Inscrire(
            @Field("nom") String nom,
            @Field("prenom") String prenom,
            @Field("identifiant") String identifiant,
            @Field("motdepasse") String password
    );

    /*************** Demande Congé *******************/
    @FormUrlEncoded
    @POST("DemandeConge.php")
    Call<ResponseDataModel> DemandeConge(
            @Field("nbjours") String nbjours,
            @Field("date") String date,
            @Field("id") String id_employe
    );

}
