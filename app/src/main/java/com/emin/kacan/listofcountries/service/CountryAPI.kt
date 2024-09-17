package com.emin.kacan.listofcountries.service

import com.emin.kacan.listofcountries.model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface CountryAPI {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries() : Single<List<Country>>
}