package com.decwujot.mclowiczinternational.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.decwujot.mclowiczinternational.R
import com.decwujot.mclowiczinternational.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view).apply {
            buttonSettings.setOnClickListener {
                findNavController().navigate(R.id.preferenceFragment)
            }
        }
    }
}