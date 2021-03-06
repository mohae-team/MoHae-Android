package com.mohaeyo.mohae.ui.fragment.main.place

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentPlaceDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModel
import kotlinx.android.synthetic.main.fragment_place_doc.*
import javax.inject.Inject

class PlaceDocFragment: BaseLocationFragment<FragmentPlaceDocBinding>() {
    @Inject
    override lateinit var viewModel: PlaceDocViewModel

    override val layoutId: Int
        get() = R.layout.fragment_place_doc

    override val mapId: Int
        get() = R.id.place_search_map

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

    override fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })

        viewModel.placeNameErrorEvent.observe(this, Observer { place_doc_title_edit_lay.error = it })

        viewModel.placeDescriptionErrorEvent.observe(this, Observer { place_doc_description_edit_lay.error = it })
    }

    private fun backToList() {
        place_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        place_doc_back_fab.doBackAnimation(false)
        parentFragment!!.parentFragment!!.findNavController().navigate(R.id.action_placeFragment_self)
    }
}