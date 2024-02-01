package com.parmhannong

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat

open class BaseActivity : Activity()
{
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}