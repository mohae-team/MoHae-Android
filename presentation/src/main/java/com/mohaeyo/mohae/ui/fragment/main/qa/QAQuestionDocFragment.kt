package com.mohaeyo.mohae.ui.fragment.main.qa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mohaeyo.data.copyStreamToFile
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaQuestionDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_question_doc.*
import javax.inject.Inject

class QAQuestionDocFragment: DataBindingFragment<FragmentQaQuestionDocBinding>() {
    @Inject
    override lateinit var viewModel: QAQuestionDocViewModel

    override val layoutId: Int
        get() = R.layout.fragment_qa_question_doc

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

    override fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })

        viewModel.titleErrorEvent.observe(this, Observer { qa_question_doc_title_edit_lay.error = it })

        viewModel.summaryErrorEvent.observe(this, Observer { qa_question_doc_summary_edit_lay.error = it })

        viewModel.descriptionErrorEvent.observe(this, Observer { qa_question_doc_description_edit_lay.error = it })

        viewModel.setImageEvent.observe(this, Observer { getLocalImage() })
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
                viewModel.questionModel.value!!.imageFile = copyStreamToFile(context!!, selectedImageUri)
                Glide.with(qa_question_doc_image_imv)
                    .load(selectedImageUri)
                    .into(qa_question_doc_image_imv)
            }
        }
    }

    private fun backToList() {
        qa_question_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        qa_question_doc_back_fab.doBackAnimation(false)
        parentFragment!!.parentFragment!!.findNavController().navigate(R.id.action_QAFragment_self)
    }
}