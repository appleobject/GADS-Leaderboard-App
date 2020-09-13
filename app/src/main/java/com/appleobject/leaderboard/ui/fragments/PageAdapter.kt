package com.appleobject.leaderboard.ui.fragments


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.appleobject.leaderboard.R

class PageAdapter(private val fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {


    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LearningLeadersFragment()
            1 -> SkillLeadersFragment()
            else -> LearningLeadersFragment()
        }
    }

    fun getPageTitle(position: Int): CharSequence = fragmentActivity.resources.getString(TAB_TITLES[position])

    override fun getItemCount(): Int = TAB_TITLES.size

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }




}