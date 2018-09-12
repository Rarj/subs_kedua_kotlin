package dev.grack.matchschedulefootbal.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.adapter.TabPageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_pager.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var tabPageAdapter: TabPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = view_pager

        tabPageAdapter = TabPageAdapter(supportFragmentManager)
        viewPager.adapter = tabPageAdapter

        tabLayout = tabs
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_calendar)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_trophy)
    }
}
