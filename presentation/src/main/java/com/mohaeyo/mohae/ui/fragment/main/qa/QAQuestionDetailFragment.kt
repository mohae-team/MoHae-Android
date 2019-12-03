package com.mohaeyo.mohae.ui.fragment.main.qa

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaQuestionDetailBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAQuestionDetailViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAQuestionDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_question_detail.*
import javax.inject.Inject

class QAQuestionDetailFragment: DataBindingFragment<FragmentQaQuestionDetailBinding>() {
    @Inject
    override lateinit var viewModel: QAQuestionDetailViewModel

    override val layoutId: Int
        get() = R.layout.fragment_qa_question_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        getArgQuestionItem()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToList()
            })
    }

    override fun observeEvent() {
        viewModel.selectedQuestionItem.observe(this, Observer {
            Glide.with(qa_question_detail_image_imv)
                .load(it.imageFile.toString())
                .into(qa_question_detail_image_imv)
        })

        viewModel.startDetailToQuestionListEvent.observe(this, Observer { backToList() })

        viewModel.startDetailToAnswerListEvent.observe(this, Observer {
            qa_question_detail_answers_fab.doCommonAnimation(R.drawable.answers_to_write_answer)
            findNavController().navigate(R.id.action_QAQuestionDetailFragment_to_QAAnswerListFragment,
                getQuestionItemBundle(viewModel.selectedQuestionItem.value!!))
        })
    }

    private fun backToList() {
        qa_question_detail_answers_fab.doCommonAnimation(R.drawable.answers_to_add)
        qa_question_detail_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_QAQuestionDetailFragment_to_QAQuestionListFragment)
    }

    private fun getArgQuestionItem() {
        viewModel.selectedQuestionId.value = arguments!!.getInt("id")
    }

    private fun getQuestionItemBundle(questionModel: QuestionModel): Bundle {
        val bundle = Bundle()
        with(questionModel) { bundle.putInt("id", id) }
        return bundle
    }
}