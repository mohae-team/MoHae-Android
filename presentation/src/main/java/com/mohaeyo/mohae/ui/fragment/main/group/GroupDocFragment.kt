package com.mohaeyo.mohae.ui.fragment.main.group

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.data.copyStreamToFile
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentGroupDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModel
import kotlinx.android.synthetic.main.fragment_group_doc.*
import javax.inject.Inject

class GroupDocFragment: BaseLocationFragment<FragmentGroupDocBinding>() {
    @Inject
    override lateinit var viewModel: GroupDocViewModel

    override val layoutId: Int
        get() = R.layout.fragment_group_doc

    override val mapId: Int = R.id.group_doc_address_map

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    private fun backToList() {
        group_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        group_doc_back_fab.doBackAnimation(false)
        parentFragment!!.parentFragment!!.findNavController().navigate(R.id.action_groupFragment_self)
    }

    override fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer {
            requireActivity().onBackPressed()
        })

        viewModel.errorEvent.observe(this, Observer {
            group_doc_title_edit_lay.error = it
            group_doc_date_edit_lay.error = it
            group_doc_summary_edit_lay.error = it
            group_doc_description_edit_lay.error = it
        })

        viewModel.setGroupImageEvent.observe(this, Observer {
            getLocalImage()
        })

    }

    private fun getLocalImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select file to upload "), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val selectedImageUri = data!!.data!!
                viewModel.postGroupModel.value!!.imageFile = copyStreamToFile(context!!, selectedImageUri)
                viewModel.postGroupModel.value = viewModel.postGroupModel.value!!
            }
        }
    }
}