package com.mohaeyo.mohae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.databinding.ItemQaQuestionListBinding
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModel

class QAQuestionListAdapter(private val viewModel: QAQuestionListViewModel): RecyclerView.Adapter<QAQuestionListAdapter.QAQuestionListViewHolder>() {

    private var questionItems = ArrayList<QuestionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAQuestionListViewHolder {
        val binding
                = ItemQaQuestionListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        binding.vm = viewModel
        return QAQuestionListViewHolder(binding)
    }


    override fun getItemCount(): Int = questionItems.size

    override fun onBindViewHolder(holder: QAQuestionListViewHolder, position: Int)
            = holder.bind(questionItems[position])

    fun setItem(questionItems: MutableLiveData<ArrayList<QuestionModel>>) {
        this.questionItems = questionItems.value!!
        notifyDataSetChanged()
    }

    inner class QAQuestionListViewHolder(val binding: ItemQaQuestionListBinding): RecyclerView.ViewHolder(binding.root.rootView) {

        fun bind(question: QuestionModel) {
            binding.setVariable(BR.questionItem, question)
        }

    }
}