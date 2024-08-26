package com.example.app08_1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomAdapter2(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val fragmentList = listOf<Fragment>(AFragment(), BFragment(), CFragment(), DFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}
