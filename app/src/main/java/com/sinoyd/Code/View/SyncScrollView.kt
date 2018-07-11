package com.sinoyd.Code.View

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView


/**
 * @Description:
 */
class SyncScrollView : ScrollView {

    var mView: View? = null


    constructor(context: Context) : super(context) {
        // TODO Auto-generated constructor stub
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // TODO Auto-generated constructor stub
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (mView != null) {
            mView!!.scrollTo(l, t)
        }


    }

    /**
     *  view
     *
     * @param view
     */
    fun setScrollView(view: View) {
        mView = view
    }


}