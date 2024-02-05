package com.parmhannong

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.facebook.react.ReactActivity

abstract class BaseActivity<T : ViewBinding> : ReactActivity() {

    private lateinit var binding: T
    abstract fun getViewBinding(): T
    private var mLoadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    open fun showToast(title: String) {
        runOnUiThread {
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }

    }

    fun showAlert(
        message: String,
        positive: String = R.string.confirm.toResString,
        positiveListener: (AlertDialog) -> Unit = {}
    ) {
        runOnUiThread {
            AlertDialog(
                this,
                message,
                positive,
//                buttonType = AlertDialog.ButtonType.SINGLE,
                negativeListener = {},
                positiveListener = positiveListener
            ).show()
        }
    }


    fun showAlert(
        message: String,
        negative: String,
        positive: String,
        negativeListener: (AlertDialog) -> Unit,
        positiveListener: (AlertDialog) -> Unit
    ) {
        runOnUiThread {
            AlertDialog(
                this,
                message,
                negative,
                positive,
//                buttonType = AlertDialog.ButtonType.DOUBLE,
                negativeListener = negativeListener,
                positiveListener = positiveListener
            ).show()
        }

    }

    fun showNetworkErrorAlert(
        negativeListener: (AlertDialog) -> Unit,
        positiveListener: (AlertDialog) -> Unit
    ) {
        runOnUiThread {
            AlertDialog(
                this,
                R.string.msg_dvat_network.toResString,
                R.string.cancel.toResString,
                R.string.retry.toResString,
//                buttonType = AlertDialog.ButtonType.DOUBLE,
                negativeListener = negativeListener,
                positiveListener = positiveListener
            ).show()
        }
    }


    fun closeModalAlert() {
        runOnUiThread {
//            ModalDialog(this, "").hide()
        }
    }

    fun showModalAlert(url: String) {
        runOnUiThread {
//            ModalDialog(this, url).show()
        }
    }

    fun showLoadingDialog(context: Context, isLoading: Boolean) {
        runOnUiThread {
            if (isLoading) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = LoadingDialog(context).apply {
                        show()
                    }
                } else {
                    mLoadingDialog?.apply {
                        show()
                    }
                }
            } else {
                mLoadingDialog?.let { dialog ->
                    if (dialog.isShowing) {
                        dialog.close()
                    }
                }
            }
        }
    }
}