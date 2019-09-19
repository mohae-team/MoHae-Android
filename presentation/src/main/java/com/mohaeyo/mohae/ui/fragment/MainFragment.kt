package com.mohaeyo.mohae.ui.fragment

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mohaeyo.mohae.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.wrapContent


class MainFragment: DaggerFragment() {

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = fragmentManager!!.beginTransaction()
        when (item.itemId) {
            R.id.navigation_feedback -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> doBackgroundAnimation(R.drawable.group_to_feedback_background)
                    R.id.navigation_mypage -> doBackgroundAnimation(R.drawable.mypage_to_feedback_background)
                    R.id.navigation_qa -> doBackgroundAnimation(R.drawable.qa_to_feedback_background)
                    R.id.navigation_place -> doBackgroundAnimation(R.drawable.place_to_feedback_background)
                    else -> doBackgroundAnimation(null)
                }
                return@OnNavigationItemSelectedListener replaceFragment(FeedbackFragment(), transaction)
            }
            R.id.navigation_qa -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> doBackgroundAnimation(R.drawable.group_to_qa_background)
                    R.id.navigation_mypage -> doBackgroundAnimation(R.drawable.mypage_to_qa_background)
                    R.id.navigation_feedback -> doBackgroundAnimation(R.drawable.feedback_to_qa_background)
                    R.id.navigation_place -> doBackgroundAnimation(R.drawable.place_to_qa_background)
                    else -> doBackgroundAnimation(null)
                }
                return@OnNavigationItemSelectedListener replaceFragment(QAFragment(), transaction)
            }
            R.id.navigation_group -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_feedback -> doBackgroundAnimation(R.drawable.feedback_to_group_background)
                    R.id.navigation_mypage -> doBackgroundAnimation(R.drawable.mypage_to_group_background)
                    R.id.navigation_qa -> doBackgroundAnimation(R.drawable.qa_to_group_background)
                    R.id.navigation_place -> doBackgroundAnimation(R.drawable.place_to_group_background)
                    else -> doBackgroundAnimation(null)
                }
                return@OnNavigationItemSelectedListener replaceFragment(GroupFragment(), transaction)
            }
            R.id.navigation_place -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> doBackgroundAnimation(R.drawable.group_to_place_background)
                    R.id.navigation_mypage -> doBackgroundAnimation(R.drawable.mypage_to_place_background)
                    R.id.navigation_qa -> doBackgroundAnimation(R.drawable.qa_to_place_background)
                    R.id.navigation_feedback -> doBackgroundAnimation(R.drawable.feedback_to_place_background)
                    else -> doBackgroundAnimation(null)
                }
                return@OnNavigationItemSelectedListener replaceFragment(PlaceFragment(), transaction)
            }
            R.id.navigation_mypage -> {
                when (main_navigation.selectedItemId) {
                    R.id.navigation_group -> doBackgroundAnimation(R.drawable.group_to_mypage_background)
                    R.id.navigation_feedback -> doBackgroundAnimation(R.drawable.feedback_to_mypage_background)
                    R.id.navigation_qa -> doBackgroundAnimation(R.drawable.qa_to_mypage_background)
                    R.id.navigation_place -> doBackgroundAnimation(R.drawable.place_to_mypage_background)
                    else -> doBackgroundAnimation(null)
                }
                return@OnNavigationItemSelectedListener replaceFragment(MyPageFragment(), transaction)
            }
        }
        false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManager!!.beginTransaction().run {
            replace(R.id.main_container, GroupFragment())
            commit()
        }
        main_navigation.selectedItemId = R.id.navigation_group
        main_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    private fun replaceFragment(fragment: Fragment, transaction: FragmentTransaction): Boolean {
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
        return true
    }

    private fun doBackgroundAnimation(resId: Int?) {
        resId?.let {
            val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
            main_background.setImageDrawable(avd)
            (main_background.drawable as Animatable).start()

        }
    }
}