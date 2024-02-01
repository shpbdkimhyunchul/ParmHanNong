package com.parmhannong

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(androidx.appcompat.R.layout.abc_action_bar_title_item)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.apply {
                startActivity(this)
                finish()
            }
        }, 3000)



    }
}