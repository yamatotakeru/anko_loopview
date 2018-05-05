package com.example.lightlim.slidingtabsbasic

import android.content.Context
import android.support.v4.view.LoopViewPager
import android.support.v4.view.ViewPager
import android.view.ViewManager
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko._RelativeLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent

/**
 * A basic sample which shows how to use [com.example.android.common.view.SlidingTabLayout]
 * to display a custom [ViewPager] title strip which gives continuous feedback to the user
 * when scrolling.
 */
class SlidingTabFragment : _RelativeLayout {

    /**
     * A custom [ViewPager] title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private var mSlidingTabLayout: SlidingTabLayout? = null

    /**
     * A [LoopViewPager] which will be used in conjunction with the [SlidingTabLayout] above.
     */
    private var mViewPager: LoopViewPager? = null

    constructor(ctx: Context) : super(ctx) {
        this.apply {
            lparams(matchParent, matchParent)
            layoutSlidingTabStrip()
            layoutLoopViewPager()
        }
    }

    fun ViewManager.layoutLoopViewPager() {
        mViewPager = ankoView({
            var layout = LoopViewPager(it)
            layout.id = R.id.sliding_view_pager
            layout
        }, theme = 0) {
            // initialize
//            lparams(matchParent, matchParent, 1f)
            lparams(matchParent, matchParent)
        }
    }

    fun ViewManager.layoutSlidingTabStrip() {
        mSlidingTabLayout = ankoView({
            SlidingTabLayout(it)
        }, theme = 0) {
            // initialize
            lparams(matchParent, dip(48))
        }
    }

    fun setAdapter(adapter: SlidingTabsAdapter) {
        mViewPager!!.adapter = adapter
        mSlidingTabLayout!!.setViewPager(mViewPager)
    }
}
