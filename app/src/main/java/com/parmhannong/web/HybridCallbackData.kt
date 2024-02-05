package com.app.cityparm.web

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class HybridCallbackData(private val tag: Int, private val method: String?, private val callbackFun: String?, private var jsonString: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        tag = parcel.readInt(),
        method = parcel.readString(),
        callbackFun = parcel.readString(),
        jsonString = parcel.readString()
    )

    fun setJsonString(jsonString: String) {
        this.jsonString = jsonString
    }

    fun getTag(): Int {
        return tag
    }

    fun getMethod(): String? {
        return method
    }

    fun getCallbackFun(): String? {
        return callbackFun
    }

    fun getJsonString(): String? {
        return jsonString
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(tag)
        dest.writeString(method)
        dest.writeString(callbackFun)
        dest.writeString(jsonString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<HybridCallbackData> {
        override fun createFromParcel(parcel: Parcel): HybridCallbackData {
            return HybridCallbackData(parcel)
        }

        override fun newArray(size: Int): Array<HybridCallbackData?> {
            return arrayOfNulls(size)
        }
    }


}