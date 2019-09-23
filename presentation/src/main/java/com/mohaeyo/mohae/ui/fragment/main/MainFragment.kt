package com.mohaeyo.mohae.ui.fragment.main

import android.content.Context
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.backButtonSubject
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMainBinding
import com.mohaeyo.mohae.viewmodel.main.MainViewModel
import com.mohaeyo.mohae.viewmodel.main.MainViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class MainFragment: DataBindingFragment<FragmentMainBinding>() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(MainViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_main

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feedback -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> {
                        doBackgroundAnimation(R.drawable.group_to_feedback_background)
                        replaceFragment(R.id.action_groupFragment_to_feedbackFragment)
                    }
                    R.id.navigation_mypage -> {
                        doBackgroundAnimation(R.drawable.mypage_to_feedback_background)
                        replaceFragment(R.id.action_myPageFragment_to_feedbackFragment)
                    }
                    R.id.navigation_qa -> {
                        doBackgroundAnimation(R.drawable.qa_to_feedback_background)
                        replaceFragment(R.id.action_QAFragment_to_feedbackFragment)
                    }
                    R.id.navigation_place -> {
                        doBackgroundAnimation(R.drawable.place_to_feedback_background)
                        replaceFragment(R.id.action_placeFragment_to_feedbackFragment)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_qa -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> {
                        doBackgroundAnimation(R.drawable.group_to_qa_background)
                        replaceFragment(R.id.action_groupFragment_to_QAFragment)
                    }
                    R.id.navigation_mypage -> {
                        doBackgroundAnimation(R.drawable.mypage_to_qa_background)
                        replaceFragment(R.id.action_myPageFragment_to_QAFragment)
                    }
                    R.id.navigation_feedback -> {
                        doBackgroundAnimation(R.drawable.feedback_to_qa_background)
                        replaceFragment(R.id.action_feedbackFragment_to_QAFragment)
                    }
                    R.id.navigation_place -> {
                        doBackgroundAnimation(R.drawable.place_to_qa_background)
                        replaceFragment(R.id.action_placeFragment_to_QAFragment)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_group -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_feedback -> {
                        doBackgroundAnimation(R.drawable.feedback_to_group_background)
                        replaceFragment(R.id.action_feedbackFragment_to_groupFragment)
                    }
                    R.id.navigation_mypage -> {
                        doBackgroundAnimation(R.drawable.mypage_to_group_background)
                        replaceFragment(R.id.action_myPageFragment_to_groupFragment)
                    }
                    R.id.navigation_qa -> {
                        doBackgroundAnimation(R.drawable.qa_to_group_background)
                        replaceFragment(R.id.action_QAFragment_to_groupFragment)
                    }
                    R.id.navigation_place -> {
                        doBackgroundAnimation(R.drawable.place_to_group_background)
                        replaceFragment(R.id.action_placeFragment_to_groupFragment)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_place -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> {
                        doBackgroundAnimation(R.drawable.group_to_place_background)
                        replaceFragment(R.id.action_groupFragment_to_placeFragment)
                    }
                    R.id.navigation_mypage -> {
                        doBackgroundAnimation(R.drawable.mypage_to_place_background)
                        replaceFragment(R.id.action_myPageFragment_to_placeFragment)
                    }
                    R.id.navigation_qa -> {
                        doBackgroundAnimation(R.drawable.qa_to_place_background)
                        replaceFragment(R.id.action_QAFragment_to_placeFragment)
                    }
                    R.id.navigation_feedback -> {
                        doBackgroundAnimation(R.drawable.feedback_to_place_background)
                        replaceFragment(R.id.action_feedbackFragment_to_placeFragment)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> {
                        doBackgroundAnimation(R.drawable.group_to_mypage_background)
                        replaceFragment(R.id.action_groupFragment_to_myPageFragment)
                    }
                    R.id.navigation_feedback -> {
                        doBackgroundAnimation(R.drawable.feedback_to_mypage_background)
                        replaceFragment(R.id.action_feedbackFragment_to_myPageFragment)
                    }
                    R.id.navigation_qa -> {
                        doBackgroundAnimation(R.drawable.qa_to_mypage_background)
                        replaceFragment(R.id.action_QAFragment_to_myPageFragment)
                    }
                    R.id.navigation_place -> {
                        doBackgroundAnimation(R.drawable.place_to_mypage_background)
                        replaceFragment(R.id.action_placeFragment_to_myPageFragment)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val backButtonSubjectDisposable: Disposable = backButtonSubject
        .buffer(2, 1)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            if (it[1] - it[0] <= 1500) activity?.finish()
            else toast("뒤로가기 버튼을 한 번 더 누르시면 종료됩니다.")
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backButtonSubject.onNext(System.currentTimeMillis())
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        doBackgroundAnimation(R.drawable.feedback_to_group_background)

        main_navigation.selectedItemId = R.id.navigation_group
        main_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        backButtonSubjectDisposable.dispose()
    }

    private fun replaceFragment(action: Int)
            = childFragmentManager.primaryNavigationFragment!!.findNavController().navigate(action)

    private fun doBackgroundAnimation(resId: Int?) {
        resId?.let {
            val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
            main_background.setImageDrawable(avd)
            (main_background.drawable as Animatable).start()
        }
    }
}