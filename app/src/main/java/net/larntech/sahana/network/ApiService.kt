package net.larntech.sahana.network

import net.larntech.sahana.model.CatalogueModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("0f78766a6d68832d309d")
    fun searchCatalogue(): Call<CatalogueModel>


}