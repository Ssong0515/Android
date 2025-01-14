package com.example.app08_viewpager2
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragAdapter2(val fragmentActivity: FragmentActivity, val mCount: Int) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 200
    }

    override fun createFragment(position: Int): Fragment {
        val index: Int = getRealPosition(position)
        return if (index == 0) OneFragment()
        else if (index == 1) TwoFragment()
        else if (index == 2 ) ThreeFragment()
        else FourFragment()
    }

    private fun getRealPosition(position: Int): Int {
        return position%mCount
    }

}
