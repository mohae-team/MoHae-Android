package com.mohaeyo.mohae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.databinding.ItemQaAnswerListBinding
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.answerList.QAAnswerListViewModel

class QAAnswerListAdapter(private val viewModel: QAAnswerListViewModel): RecyclerView.Adapter<QAAnswerListAdapter.QAAnswerListViewHolder>() {

    private var answerItems = ArrayList<QuestionModel.AnswerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAAnswerListViewHolder {
        val binding
                = ItemQaAnswerListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        binding.vm = viewModel
        return QAAnswerListViewHolder(binding)
    }


    override fun getItemCount(): Int = answerItems.size

    override fun onBindViewHolder(holder: QAAnswerListViewHolder, position: Int)
            = holder.bind(answerItems[position])

    fun setItem(answerItems: MutableLiveData<ArrayList<QuestionModel.AnswerModel>>) {
        this.answerItems = answerItems.value!!
        notifyDataSetChanged()
    }

    inner class QAAnswerListViewHolder(val binding: ItemQaAnswerListBinding): RecyclerView.ViewHolder(binding.root.rootView) {

        fun bind(answer: QuestionModel.AnswerModel) {
            binding.setVariable(BR.answerItem, answer)
        }

    }
}