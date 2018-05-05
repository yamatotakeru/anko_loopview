package com.example.lightlim.slidingtabsbasic

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewManager
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = SlidingTabsAdapter(fragmentManager)
        adapter.items = createFragments()

        frameLayout {
            slidingTabBasicFragment {
                setAdapter(adapter)
            }
        }
    }

    fun createFragments() : List<PageFragment> {
        return listOf(
                PageFragment().apply {
                    title = "ほげ１"
                    var view = linearLayout {
                        gravity = Gravity.CENTER
                        button {
                            text = "ほげほげ１"
                        }.lparams(dip(100), matchParent)
                        lparams(matchParent, matchParent)
                    }
                },
                PageFragment().apply {
                    title = "ほげ２"
                    var view = linearLayout {
                        gravity = Gravity.CENTER
                        button {
                            text = "ほげほげ２"
                        }.lparams(dip(100), matchParent)
                        lparams(matchParent, matchParent)
                    }
                })
    }
}

inline fun ViewManager.slidingTabBasicFragment(init: SlidingTabFragment.() -> Unit): SlidingTabFragment {
    return ankoView({
        SlidingTabFragment(it)
    }, theme = 0) {
        init()
    }
}