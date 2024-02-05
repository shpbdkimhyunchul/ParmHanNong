package com.parmhannong

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.WindowManager
import com.parmhannong.databinding.ViewLoadingDialogBinding


class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialogTheme) {

    private val binding by lazy {
        ViewLoadingDialogBinding.inflate(layoutInflater)
    }

    init {
        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.4f
        window!!.attributes = lpWindow

        ViewLoadingDialogBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setCancelable(true)

        (binding.ivLoading.background as? AnimationDrawable)?.start()
    }

    fun close() {
        this.dismiss()
    }
}