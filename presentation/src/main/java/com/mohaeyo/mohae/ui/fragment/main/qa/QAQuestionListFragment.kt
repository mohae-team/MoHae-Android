package com.mohaeyo.mohae.ui.fragment.main.qa

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.adapter.QAQuestionListAdapter
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaQuestionListBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModelFactory
import kotlinx.android.synthetic.main.fragment_qa_question_list.*
import javax.inject.Inject

class QAQuestionListFragment: EndPointDataBindingFragment<FragmentQaQuestionListBinding>() {

    @Inject
    lateinit var factory: QAQuestionListViewModelFactory

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(QAQuestionListViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_qa_question_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.qaQuestionList.layoutManager = LinearLayoutManager(context)
        binding.qaQuestionList.adapter = QAQuestionListAdapter(viewModel)
    }

    override fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            qa_question_list_add_fab.doCommonAnimation(R.drawable.add_to_check)
            qa_question_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_QAQuestionListFragment_to_QAQuestionDocFragment)
        })

        viewModel.listAnimationEvent.observe(this, Observer {
            binding.qaQuestionList.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_bottom)
            binding.qaQuestionList.scheduleLayoutAnimation()
        })

        viewModel.startListToDetailEvent.observe(this, Observer {
            qa_question_list_add_fab.doCommonAnimation(R.drawable.add_to_answers)
            qa_question_list_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_QAQuestionListFragment_to_QAQuestionDetailFragment, getQuestionItemBundle(it))
        })
    }

    private fun getQuestionItemBundle(questionModel: QuestionModel): Bundle {
        val bundle = Bundle()
        with(questionModel) { bundle.putInt("id", id) }

        return bundle
    }
}