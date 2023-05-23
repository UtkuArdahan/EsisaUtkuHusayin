package com.example.esisaandroid.ui.satici.saticiUrunGuncelleme

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esisaandroid.R


class ProductUpdatesFragment : Fragment() {

    companion object {
        fun newInstance() = ProductUpdatesFragment()
    }

    private lateinit var viewModel: ProductUpdatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_satici_urunguncelleme, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductUpdatesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}