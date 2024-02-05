package com.parmhannong

import android.app.Dialog
import android.content.Context
import com.parmhannong.R
import com.parmhannong.databinding.ViewDefaultDialogBinding


class AlertDialog(
    context: Context, message: String = "",
    negative: String = R.string.cancel.toResString, positive: String = R.string.confirm.toResString,
    buttonType: ButtonType = ButtonType.SINGLE,
    negativeListener: (AlertDialog) -> Unit = {},
    positiveListener: (AlertDialog) -> Unit = {}
) : Dialog(context, R.style.DialogFadeAnim) {

    private var onPositiveButtonListener: ((AlertDialog) -> Unit)? = null
    private var onNegativeButtonListener: ((AlertDialog) -> Unit)? = null

    private val binding by lazy {
        ViewDefaultDialogBinding.inflate(layoutInflater)
    }

    enum class ButtonType {
        SINGLE,
        DOUBLE
    }

    init {
        setContentView(binding.root)
        setCancelable(false)

        binding.tvMessage.text = message
        binding.tvPositive.text = positive
        onPositiveButtonListener = positiveListener

        binding.tvNegative.toGone()
        if (buttonType == ButtonType.DOUBLE) {
            binding.tvNegative.toVisible()
            binding.tvNegative.text = negative
            onNegativeButtonListener = negativeListener
        }
    }


    override fun show() {
        binding.tvPositive.setOnClickListener {
            onPositiveButtonListener?.let {
                onPositiveButtonListener!!(this)
            }

            this.dismiss()
        }

        binding.tvNegative.setOnClickListener {
            onNegativeButtonListener?.let {
                onNegativeButtonListener!!(this)
            }
            this.dismiss()
        }

        super.show()
    }

}