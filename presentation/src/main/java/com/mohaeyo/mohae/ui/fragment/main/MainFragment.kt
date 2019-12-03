package com.mohaeyo.mohae.ui.fragment.main

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMainBinding
import com.mohaeyo.mohae.viewmodel.main.MainViewModel
import com.mohaeyo.mohae.viewmodel.main.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment: EndPointDataBindingFragment<FragmentMainBinding>() {
    @Inject
    override lateinit var viewModel: MainViewModel

    override val layoutId: Int
        get() = R.layout.fragment_main

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feedback -> return@OnNavigationItemSelectedListener selectFeedback()
            R.id.navigation_qa -> return@OnNavigationItemSelectedListener selectQA()
            R.id.navigation_group -> return@OnNavigationItemSelectedListener selectGroup()
            R.id.navigation_place -> return@OnNavigationItemSelectedListener selectPlace()
            R.id.navigation_mypage -> return@OnNavigationItemSelectedListener selectMyPage()
        }
        false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        doBackgroundAnimation(R.drawable.feedback_to_group_background)

        main_navigation.selectedItemId = R.id.navigation_group
        main_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    override fun observeEvent() {

    }

    private fun replaceFragment(animationResId: Int?, action: Int) {
        doBackgroundAnimation(animationResId)
        childFragmentManager.primaryNavigationFragment!!.findNavController().navigate(action)
    }

    private fun doBackgroundAnimation(resId: Int?) {
        resId?.let {
            val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
            main_background.setImageDrawable(avd)
            (main_background.drawable as Animatable).start()
        }
    }

    private fun selectFeedback(): Boolean {
        when (main_navigation.selectedItemId) {
            R.id.navigation_group ->
                replaceFragment(R.drawable.group_to_feedback_background, R.id.action_groupFragment_to_feedbackFragment)
            R.id.navigation_mypage ->
                replaceFragment(R.drawable.mypage_to_feedback_background, R.id.action_myPageFragment_to_feedbackFragment)
            R.id.navigation_qa ->
                replaceFragment(R.drawable.qa_to_feedback_background, R.id.action_QAFragment_to_feedbackFragment)
            R.id.navigation_place ->
                replaceFragment(R.drawable.place_to_feedback_background, R.id.action_placeFragment_to_feedbackFragment)
        }
        return true
    }

    private fun selectPlace(): Boolean {
        when (main_navigation.selectedItemId) {
            R.id.navigation_group ->
                replaceFragment(R.drawable.group_to_place_background, R.id.action_groupFragment_to_placeFragment)
            R.id.navigation_mypage ->
                replaceFragment(R.drawable.mypage_to_place_background, R.id.action_myPageFragment_to_placeFragment)
            R.id.navigation_qa ->
                replaceFragment(R.drawable.qa_to_place_background, R.id.action_QAFragment_to_placeFragment)
            R.id.navigation_feedback ->
                replaceFragment(R.drawable.feedback_to_place_background, R.id.action_feedbackFragment_to_placeFragment)
        }
        return true
    }

    private fun selectQA(): Boolean {
        when (main_navigation.selectedItemId) {
            R.id.navigation_group ->
                replaceFragment(R.drawable.group_to_qa_background, R.id.action_groupFragment_to_QAFragment)
            R.id.navigation_mypage ->
                replaceFragment(R.drawable.mypage_to_qa_background, R.id.action_myPageFragment_to_QAFragment)
            R.id.navigation_feedback ->
                replaceFragment(R.drawable.feedback_to_qa_background, R.id.action_feedbackFragment_to_QAFragment)
            R.id.navigation_place ->
                replaceFragment(R.drawable.place_to_qa_background, R.id.action_placeFragment_to_QAFragment)
        }
        return true
    }

    private fun selectGroup(): Boolean {
        when (main_navigation.selectedItemId) {
            R.id.navigation_feedback ->
                replaceFragment(R.drawable.feedback_to_group_background, R.id.action_feedbackFragment_to_groupFragment)
            R.id.navigation_mypage ->
                replaceFragment(R.drawable.mypage_to_group_background,  R.id.action_myPageFragment_to_groupFragment)
            R.id.navigation_qa ->
                replaceFragment(R.drawable.qa_to_group_background, R.id.action_QAFragment_to_groupFragment)
            R.id.navigation_place ->
                replaceFragment(R.drawable.place_to_group_background, R.id.action_placeFragment_to_groupFragment)
        }
        return true
    }

    private fun selectMyPage(): Boolean {
        when (main_navigation.selectedItemId) {
            R.id.navigation_group ->
                replaceFragment(R.drawable.group_to_mypage_background, R.id.action_groupFragment_to_myPageFragment)
            R.id.navigation_feedback ->
                replaceFragment(R.drawable.feedback_to_mypage_background, R.id.action_feedbackFragment_to_myPageFragment)
            R.id.navigation_qa ->
                replaceFragment(R.drawable.qa_to_mypage_background, R.id.action_QAFragment_to_myPageFragment)
            R.id.navigation_place ->
                replaceFragment(R.drawable.place_to_mypage_background,  R.id.action_placeFragment_to_myPageFragment)
        }
        return true
    }
}