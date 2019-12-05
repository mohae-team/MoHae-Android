package com.mohaeyo.mohae.ui.fragment.main.feedback

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.data.copyStreamToFile
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentFeedbackDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModel
import kotlinx.android.synthetic.main.fragment_feedback_doc.*
import javax.inject.Inject

class FeedbackDocFragment: BaseLocationFragment<FragmentFeedbackDocBinding>() {

    override val mapId: Int
        get() = R.id.feedback_doc_search_map

    @Inject
    override lateinit var viewModel: FeedbackDocViewModel

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
        binding.vm = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val selectedImageUri = data!!.data!!
                viewModel.feedbackModel.value!!.imageFile = copyStreamToFile(context!!, selectedImageUri)
                viewModel.feedbackModel.value = viewModel.feedbackModel.value!!
            }
        }
    }

    override fun observeEvent() {
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

    private fun backToList() {
        feedback_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        feedback_doc_back_fab.doBackAnimation(false)
        parentFragment!!.parentFragment!!.findNavController().navigate(R.id.action_feedbackFragment_self)
    }
}