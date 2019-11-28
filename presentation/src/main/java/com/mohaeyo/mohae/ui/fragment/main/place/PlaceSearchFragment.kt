package com.mohaeyo.mohae.ui.fragment.main.place

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.databinding.FragmentPlaceSearchBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModelFactory
import kotlinx.android.synthetic.main.fragment_place_search.*
import javax.inject.Inject
import android.os.Looper
import com.mohaeyo.mohae.base.EndPointLocationFragment
import org.jetbrains.anko.support.v4.toast

class PlaceSearchFragment: EndPointLocationFragment<FragmentPlaceSearchBinding>() {

    @Inject
    lateinit var factory: PlaceSearchViewModelFactory

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(PlaceSearchViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_place_search

    override val mapId: Int
        get() = R.id.place_search_map

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel

    }

    override fun observeEvent() {
        viewModel.startSearchToDocEvent.observe(this, Observer {
            place_search_add_fab.doCommonAnimation(R.drawable.add_to_check)
            place_search_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_placeSearchFragment_to_placeDocFragment)
        })

        viewModel.placeIsLike.observe(this, Observer {
            if (it) place_search_name_card_like_check.setBackgroundResource(R.drawable.ic_favorite_pink)
            else place_search_name_card_like_check.setBackgroundResource(R.drawable.ic_favorite_border_black)
        })
    }
}