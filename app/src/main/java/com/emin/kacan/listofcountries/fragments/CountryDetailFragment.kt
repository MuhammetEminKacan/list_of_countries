package com.emin.kacan.listofcountries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.emin.kacan.listofcountries.R
import com.emin.kacan.listofcountries.databinding.FragmentCountryDetailBinding
import com.emin.kacan.listofcountries.util.downloadFromUrl
import com.emin.kacan.listofcountries.util.placeHolderProggesBar
import com.emin.kacan.listofcountries.viewModel.DetailViewModel
import com.emin.kacan.listofcountries.viewModel.FeedViewModel

class CountryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCountryDetailBinding
    private lateinit var viewModel : DetailViewModel
    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).CountryUuid
        }

        viewModel.getDataFromRoom(countryUuid)


        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
               binding.selectedCountry = country
            }
        })
    }
}