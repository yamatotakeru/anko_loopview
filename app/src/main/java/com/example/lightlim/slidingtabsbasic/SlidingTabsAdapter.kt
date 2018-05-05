package com.example.lightlim.slidingtabsbasic

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*

class PageFragment : Fragment() {
    var title: String = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println("onCreateView")
        println(container)
//        return super.onCreateView(inflater, container, savedInstanceState)
        title = "ほげ１"
        var view: View? = null
        AnkoContext.create(ctx).apply {
            view = linearLayout {
                gravity = Gravity.CENTER
                button {
                    text = "ほげほげ１"
                }.lparams(dip(100), matchParent)
                lparams(matchParent, matchParent)
            }
        }
        return view!!
    }
}

/**
 * The [android.support.v4.view.PagerAdapter] used to display pages in this sample.
 * The individual pages are simple and just display two lines of text. The important section of
 * this class is the [.getPageTitle] method which controls what is displayed in the
 * [SlidingTabLayout].
 */
public class SlidingTabsAdapter(var fragmentManager: FragmentManager) : PagerAdapter() {

    var items: List<PageFragment>? = null

    /**
     * @return the number of pages to display
     */
    override fun getCount(): Int {
        return if (items != null) items!!.size else 0
    }

    /**
     * @return true if the value returned from [.instantiateItem] is the
     * same object as the [View] added to the [ViewPager].
     */
    override fun isViewFromObject(view: View, o: Any): Boolean {
        return o === view
    }

    // BEGIN_INCLUDE (pageradapter_getpagetitle)
    /**
     * Return the title of the item at `position`. This is important as what this method
     * returns is what is displayed in the [SlidingTabLayout].
     *
     *
     * Here we construct one using the position value, but for real application the title should
     * refer to the item's contents.
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return if (items != null && position < items!!.size) items!![position].title else ""
    }
    // END_INCLUDE (pageradapter_getpagetitle)

    /**
     * Instantiate the [View] which should be displayed at `position`. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // todo: アイテムを生成する
        // 登録したフラグメントクラスから生成する
        // キャッシュするかどうかをオプション化
        println("instantiateItem() [position: $position]")

        var view: Fragment? = null

        if (items != null) {
            view = items!![position]
        } else {
            view = PageFragment().apply {

            }
        }

//        container.addView(view as View?)

        val transaction =  fragmentManager.beginTransaction()
        transaction.add(container.id, view)
        transaction.commit()

//        container.apply {
//            view = linearLayout {
//                gravity = Gravity.CENTER
//                button {
//                    text = "ほげほげ $position"
//                    onClick {
//                        println("push [position: $position]")
//                    }
//                }.lparams(dip(100), matchParent)
//                lparams(matchParent, matchParent)
//            }
//        }

        return view!!
    }

    /**
     * Destroy the item from the [ViewPager]. In our case this is simply removing the
     * [View].
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val transaction =  fragmentManager.beginTransaction()
        transaction.remove(`object` as Fragment)
        transaction.commit()

//        container.removeView(`object` as View)
        println("destroyItem() [position: $position]")
    }

}
