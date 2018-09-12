package dev.grack.matchschedulefootbal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dev.grack.matchschedulefootbal.fragment.NextMatchFragment
import dev.grack.matchschedulefootbal.fragment.PastMatchFragment

class TabPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    val PAGE_COUNT = 2
    val titleTab = arrayOf<String>("Next Match", "Past Match")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                NextMatchFragment()
            }
            1 -> PastMatchFragment()
            else -> {
                return null
            }
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleTab[position]
    }
}