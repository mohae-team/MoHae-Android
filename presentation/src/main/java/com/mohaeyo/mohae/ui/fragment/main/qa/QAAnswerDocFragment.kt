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
    lateinit var factory: QAAnswerDocViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(QAAnswerDocViewModel::class.java) }

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

        getArgQuestionItem()
        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })
    }

    private fun backToList() {
        qa_answer_doc_post_fab.doCommonAnimation(R.drawable.check_to_write_answer)
        findNavController().navigate(R.id.action_QAAnswerDocFragment_to_QAAnswerListFragment,
            getQuestionIdBundle(viewModel.selectedQuestionId.value!!))
    }

    private fun getArgQuestionItem() {
        viewModel.selectedQuestionId.value = arguments!!.getInt("id")
    }

    private fun getQuestionIdBundle(id: Int): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", id)

        return bundle
    }
}