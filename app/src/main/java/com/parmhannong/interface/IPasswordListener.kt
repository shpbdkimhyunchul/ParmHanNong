package com.parmhannong

import org.json.JSONObject

interface IPasswordListener {
    fun PasswordSuccess(jsonObject: JSONObject)
    fun PasswordFail()
}


