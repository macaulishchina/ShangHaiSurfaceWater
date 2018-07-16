package com.sinoyd

import android.content.Context
import android.support.v4.content.FileProvider
import com.macaulish.top.velvet.util.UriKits

/**
 * created by hu
 * at 2018/7/8
 * in project salmon
 * description
 */
class Provider : FileProvider(){
    companion object {
        fun getUriKits(context: Context):UriKits{
            return UriKits(context, context.getPackageName() + ".Provider")
        }
    }
}