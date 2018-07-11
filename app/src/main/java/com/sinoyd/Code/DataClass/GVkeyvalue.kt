package com.sinoyd.Code.DataClass

import android.util.Log


/**
 * Created by Sinoyd on 2017/12/22.
 */
data class GVkeyvalue(var keytime: GVkeytime, var valueunit: GVkeytime, var kv: String)

data class GVkeyvaluesimple(var key: String, var value: String, var kv: String)

data class GVkeytime(var keyx: String, var timey: String)

data class Keyvalue(var keyid: String, var valuename: String, var isselected: Boolean = false)

//单击进行判断进行数据整理
fun changedata(i: Int, listkeyvaluename: ArrayList<Keyvalue>, only: Boolean): ArrayList<Keyvalue> {
    Log.i("scj", "单选吗？：$only")
    if (only) {
        for (item in listkeyvaluename) {
            item.isselected = false
        }
        listkeyvaluename[i].isselected = true
    } else {
        if (judge(i, listkeyvaluename)) {

        } else {
            listkeyvaluename[i].isselected = !(listkeyvaluename[i].isselected)
        }

    }
    return listkeyvaluename
}

//多选的时候，判断  当只有一个true的时候，点击的是不是当前true的那个
fun judge(postion: Int, list: ArrayList<Keyvalue>): Boolean {
    var n = 0  //当前有几个true
    var j = 0  //当前是true的位置
    for ((x, item) in list.withIndex()) {
        if (item.isselected) {
            n++
            j = x
        }
    }

    if (n == 1) {
        if (postion == j) {
            return true
        }
    }

    return false

}

