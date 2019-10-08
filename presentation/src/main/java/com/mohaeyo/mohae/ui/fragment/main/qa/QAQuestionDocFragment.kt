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
import com.mohaeyo.mohae.databinding.FragmentQaQuestionDocBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_question_doc.*
import javax.inject.Inject

class QAQuestionDocFragment: DataBindingFragment<FragmentQaQuestionDocBinding>() {


    @Inject
    lateinit var factory: QAQuestionDocViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(QAQuestionDocViewModel::class.java) }

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

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startDocToListEvent.observe(this, Observer { backToList() })
    }

    private fun backToList() {
        qa_question_doc_post_fab.doCommonAnimation(R.drawable.check_to_add)
        qa_question_doc_back_fab.doBackAnimation(false)
        findNavController().navigate(R.id.action_QAQuestionDocFragment_to_QAQuestionListFragment)
    }
}