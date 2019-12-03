package com.mohaeyo.mohae.ui.fragment.main.qa

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.QAAnswerListAdapter
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaAnswerListBinding
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.answerList.QAAnswerListViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerListViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_answer_list.*
import javax.inject.Inject

class QAAnswerListFragment: DataBindingFragment<FragmentQaAnswerListBinding>() {
    @Inject
    override lateinit var viewModel: QAAnswerListViewModel

    override val layoutId: Int
        get() = R.layout.fragment_qa_answer_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.qaAnswerList.layoutManager = LinearLayoutManager(context)
        binding.qaAnswerList.adapter = QAAnswerListAdapter(viewModel)

        getArgQuestionItem()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToDetail()
            })
    }

    override fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            qa_answer_list_answer_doc_fab.doCommonAnimation(R.drawable.write_answer_to_check)
            findNavController().navigate(R.id.action_QAAnswerListFragment_to_QAAnswerDocFragment,
                getQuestionItemBundle(viewModel.selectedQuestionId.value!!))
        })

        viewModel.listAnimationEvent.observe(this, Observer {
            binding.qaAnswerList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom)
            binding.qaAnswerList.scheduleLayoutAnimation()
        })

        viewModel.startListToDetailEvent.observe(this, Observer { backToDetail() })
    }

    private fun backToDetail() {
        qa_answer_list_answer_doc_fab.doCommonAnimation(R.drawable.write_answer_to_answers)
        findNavController().navigate(R.id.action_QAAnswerListFragment_to_QAQuestionDetailFragment,
            getQuestionItemBundle(viewModel.selectedQuestionId.value!!))
    }

    private fun getArgQuestionItem() {
        viewModel.selectedQuestionId.value = arguments!!.getInt("id")
    }

    private fun getQuestionItemBundle(questionId: Int): Bundle {
        val bundle = Bundle()
        bundle.putInt("id", questionId)
        return bundle
    }
}