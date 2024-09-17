package com.emin.kacan.listofcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emin.kacan.listofcountries.R
import com.emin.kacan.listofcountries.databinding.ItemCountryBinding
import com.emin.kacan.listofcountries.fragments.FeedFragmentDirections
import com.emin.kacan.listofcountries.model.Country
import com.emin.kacan.listofcountries.util.downloadFromUrl
import com.emin.kacan.listofcountries.util.placeHolderProggesBar

class CountryAdapter(val countryList:ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(var binding : ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val a = holder.binding
        val b = countryList[position]
        a.country = b
        a.cardLayout.setOnClickListener {
           val action = FeedFragmentDirections.actionFeedFragmentToCountryDetailFragment(b.uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }


}