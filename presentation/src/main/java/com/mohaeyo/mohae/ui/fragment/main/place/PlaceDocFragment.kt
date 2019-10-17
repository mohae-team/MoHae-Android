package com.mohaeyo.mohae.ui.fragment.main.place

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentPlaceDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_place_doc.*
import javax.inject.Inject

class PlaceDocFragment: DataBindingFragment<FragmentPlaceDocBinding>() {


    @Inject
    lateinit var factory: PlaceDocViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(PlaceDocViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_place_doc

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel
    }

    private fun backToList() {
        place_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        place_doc_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_placeDocFragment_to_placeSearchFragment)
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })
    }
}