package com.emin.kacan.listofcountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emin.kacan.listofcountries.model.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries:Country) : List<Long>

    @Query("SELECT * FROM Country ")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM Country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()
}