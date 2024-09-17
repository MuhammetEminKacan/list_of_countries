package com.emin.kacan.listofcountries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emin.kacan.listofcountries.adapter.CountryAdapter
import com.emin.kacan.listofcountries.databinding.FragmentFeedBinding
import com.emin.kacan.listofcountries.viewModel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var binding:FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel :FeedViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        binding.rvCountryList.layoutManager = LinearLayoutManager(context)
        binding.rvCountryList.adapter = countryAdapter

        binding.swiperefreshlayout.setOnRefreshListener {
            binding.rvCountryList.visibility = View.GONE
            binding.countryErorMessage.visibility = View.GONE
            binding.pbCountryList.visibility = View.VISIBLE
            viewModel.refreshFromApi()
            binding.swiperefreshlayout.isRefreshing = false
        }

        observerLiveData()
    }

    fun observerLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.rvCountryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { eror ->
            eror?.let {
                if(it){
                    binding.countryErorMessage.visibility = View.VISIBLE
                }else{
                    binding.countryErorMessage.visibility = View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    binding.pbCountryList.visibility = View.VISIBLE
                    binding.rvCountryList.visibility = View.GONE
                    binding.countryErorMessage.visibility = View.GONE
                }else{
                    binding.pbCountryList.visibility = View.GONE
                }
            }
        })
    }



}