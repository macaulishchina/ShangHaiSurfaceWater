package com.sinoyd.Code.DataClass

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

/**
 * created by macaulish
 * at 2018/7/7
 * in project salmon
 * description
 */
class IntegratorInfo {

    /**
    {
    "RowGuid": "fd412cf6-ca8b-45ae-9efd-7518232e2d50",
    "ItemText": "无锡中科"
    }
     */
    @SerializedName("ItemText")
    var name : String = ""

    var RowGuid : String = ""

    override fun toString(): String {
        return name
    }

}