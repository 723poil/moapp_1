package com.moapp.letyouknowrecyclingapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity)
{
    val fragments: List<Fragment>

    init {
        fragments= listOf(Tab1Fragment(), Tab2Fragment(), Tab3Fragment(), Tab4Fragment(), Tab5Fragment(), Tab6Fragment(), Tab7Fragment(), Tab8Fragment())
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}