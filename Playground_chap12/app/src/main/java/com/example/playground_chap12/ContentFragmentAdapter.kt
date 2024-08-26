package com.example.playground_chap12

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContentFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    val fragments = listOf<Fragment>(FirstFragment(), SecondFragment(), ThirdFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}
