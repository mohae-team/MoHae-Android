package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohaeyo.data.copyStreamToFile
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_feedback_doc.*
import kotlinx.android.synthetic.main.fragment_group_doc.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class FeedbackDocFragment: BaseLocationFragment<FragmentFeedbackDocBinding>() {

    override val mapId: Int
        get() = R.id.feedback_doc_search_map

    @Inject
    lateinit var factory: FeedbackDocViewModelFactory

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(FeedbackDocViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_feedback_doc

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

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })

        viewModel.summaryErrorEvent.observe(this, Observer { feedback_doc_summary_edit_lay.error = it })

        viewModel.descriptionErrorEvent.observe(this, Observer { feedback_doc_description_edit_lay.error = it })

        viewModel.setFeedbackImageEvent.observe(this, Observer { getLocalImage() })
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
                viewModel.feedbackModel.value!!.imageFile = copyStreamToFile(context!!, selectedImageUri)
                Glide.with(feedback_doc_image_imv)
                    .load(selectedImageUri)
                    .into(feedback_doc_image_imv)
            }
        }
    }

    private fun backToList() {
        feedback_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        feedback_doc_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_feedbackDocFragment_to_feedbackListFragment)
    }
}