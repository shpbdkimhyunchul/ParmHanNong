package com.parmhannong

import com.app.cityparm.web.HybridCallbackData
import org.json.JSONObject

interface HybridCallbackable {
    fun getData(): HybridCallbackData
    fun didWork(params: JSONObject)
    fun didWork(rsultCode: String, msg: String, params: JSONObject)
}