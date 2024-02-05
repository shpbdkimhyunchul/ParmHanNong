package com.parmhannong

import android.content.Context
import android.content.SharedPreferences
import java.util.HashSet

object PrefUtil {

    private const val NAME = "com.dgbcap.moffice"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun initSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun setString(key: String, value: String) {
        preferences.edit { it.putString(key, value) }
    }

    fun getString(key: String): String = preferences.getString(key, "").toString()


    fun setInt(key: String, value: Int) {
        preferences.edit { it.putInt(key, value) }
    }

    fun getInt(key: String): Int = preferences.getInt(key, 0)


    fun setBoolean(key: String, value: Boolean) {
        preferences.edit { it.putBoolean(key, value) }
    }

    fun getBoolean(key: String): Boolean = preferences.getBoolean(key, false)

    fun setStringSet(key: String, value: Set<String>) {
        preferences.edit { it.putStringSet(key, value) }
    }

    fun getStringSet(key: String): Set<String>? = preferences.getStringSet(key, HashSet<String>())

}