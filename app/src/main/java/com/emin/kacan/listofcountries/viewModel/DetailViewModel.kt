package com.emin.kacan.listofcountries.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emin.kacan.listofcountries.model.Country
import com.emin.kacan.listofcountries.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application : Application) :BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int){
       launch {
           val dao = CountryDatabase(getApplication()).countrydao()
          val country =  dao.getCountry(uuid)

           countryLiveData.value = country
       }
    }
}