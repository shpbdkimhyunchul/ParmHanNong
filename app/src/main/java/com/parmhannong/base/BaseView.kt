package com.parmhannong

import android.content.Context


/**
 * BaseView<T>
 * 베이스 뷰 인터페이스
 */
interface BaseView<T> {
    fun showLoadingDialog(context: Context, isLoading: Boolean)
    fun showToast(title: String)
    fun showAlert(message: String, positive: String = R.string.confirm.toResString, positiveListener: (AlertDialog) -> Unit = {})
    fun showAlert(message: String, negative: String, positive: String, negativeListener: (AlertDialog) -> Unit, positiveListener: (AlertDialog) -> Unit)
    fun showNetworkErrorAlert(negativeListener: (AlertDialog) -> Unit, positiveListener: (AlertDialog) -> Unit)

}