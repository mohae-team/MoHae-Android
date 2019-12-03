package com.mohaeyo.mohae.ui.fragment.main.qa

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaAnswerDocBinding
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.qa.answerDoc.QAAnswerDocViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_answer_doc.*
import javax.inject.Inject

class QAAnswerDocFragment: DataBindingFragment<FragmentQaAnswerDocBinding>() {
    @Inject
    override lateinit var viewModel: QAAnswerDocViewModel

    override val layoutId: Int
        get() = R.layout.fragment_qa_answer_doc

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

        getArgQuestionItem()
    }

    override fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })

        viewModel.answerErrorEvent.observe(this, Observer { qa_answer_doc_answer_edit_lay.error = it })
    }

    private fun backToList() {
        qa_answer_doc_post_fab.doCommonAnimation(R.drawable.check_to_write_answer)
        parentFragment!!.parentFragment!!.findNavController().navigate(R.id.action_QAFragment_self,
            getQuestionIdBundle(viewModel.answerModel.value!!.questionId))
    }

    private fun getArgQuestionItem() {
        viewModel.answerModel.value!!.questionId = arguments!!.getInt("id")
    }

    private fun getQuestionIdBundle(id: Int): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)

        return bundle
    }
}