package com.mohaeyo.mohae.bindingAdapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohaeyo.mohae.adapter.FeedbackListAdapter
import com.mohaeyo.mohae.adapter.GroupListAdapter
import com.mohaeyo.mohae.adapter.QAAnswerListAdapter
import com.mohaeyo.mohae.adapter.QAQuestionListAdapter
import com.mohaeyo.mohae.model.AnswerModel
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.model.QuestionModel
import java.io.File

@BindingAdapter("groupItems")
fun RecyclerView.bindGroupItems(groupItems: MutableLiveData<ArrayList<GroupModel>>) {
    (adapter as GroupListAdapter).setItem(groupItems)
}

@BindingAdapter("feedbackItems")
fun RecyclerView.bindFeedbackItems(feedbackItems: MutableLiveData<ArrayList<FeedbackModel>>) {
    (adapter as FeedbackListAdapter).setItem(feedbackItems)
}

@BindingAdapter("questionItems")
fun RecyclerView.bindQuestionItems(questionItems: MutableLiveData<ArrayList<QuestionModel>>) {
    (adapter as QAQuestionListAdapter).setItem(questionItems)
}

@BindingAdapter("answerItems")
fun RecyclerView.bindAnswerItems(answerItems: MutableLiveData<ArrayList<AnswerModel>>) {
    (adapter as QAAnswerListAdapter).setItem(answerItems)
}

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(imageFile: File) {
    Glide.with(this)
        .load(imageFile.toString())
        .into(this)
}

@BindingAdapter("setCircleImage")
fun AppCompatImageView.setCircleImage(imageFile: File) {
    Glide.with(this)
        .load(imageFile.toString())
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}