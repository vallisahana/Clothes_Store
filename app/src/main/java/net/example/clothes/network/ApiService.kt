package net.example.clothes.network

import net.example.clothes.model.CatalogueModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {


    @GET("0f78766a6d68832d309d")
    fun searchCatalogue(): Call<CatalogueModel>


}