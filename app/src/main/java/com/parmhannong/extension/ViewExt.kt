package com.parmhannong

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import java.io.File

fun View.toGone() {
    this.isVisible = false
}

fun View.toVisible() {
    this.isVisible = true
}

fun View.toInvisible() {
    this.isInvisible = true
}

fun View.isFadeOut(): Boolean {
    return this.alpha == 0f
}

fun ImageView.startBackgroundAnimation() {
    (this.background as AnimationDrawable).start()
}

fun ImageView.stopBackgroundAnimation() {
    (this.background as AnimationDrawable).stop()
}

fun ImageView.toVisibleAndStartAnimation() {
    this.visibility = View.VISIBLE
    (this.background as AnimationDrawable).start()
}

fun ImageView.toGoneAndStopAnimation() {
    this.visibility = View.GONE
    (this.background as AnimationDrawable).stop()
}

fun View.showFromTop(duration: Long) {
    clearAnimation()
    visibility = View.VISIBLE
    translationY = (-height).toFloat()
    ViewCompat.animate(this)
        .translationY(0f)
        .setDuration(duration)
        .start()
}


fun View.hideToTop(duration: Long) {
    clearAnimation()
    val propertyAnimator = ViewCompat.animate(this)
        .translationY((-height).toFloat())
        .setDuration(duration)
    propertyAnimator
        .setListener(object : ViewPropertyAnimatorListener {
            override fun onAnimationStart(view: View) {
            }

            override fun onAnimationEnd(view: View) {
                visibility = View.INVISIBLE
                propertyAnimator.setListener(null)
            }

            override fun onAnimationCancel(view: View) {
            }
        })
        .start()
}

fun View.startFadeInAnim() {
    this.animate().setDuration(500)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .alpha(1f)
        .start()
}

fun TextView.inputTextBoldType(str: String) {
    this.setTypeface(null, if (!str.isNullOrEmpty()) Typeface.BOLD else Typeface.NORMAL)
}

fun TextView.setAppearance(context: Context, res: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        @Suppress("DEPRECATION")
        this.setTextAppearance(context, res)
    } else {
        this.setTextAppearance(res)
    }
}

/**
 * 소프트 키보드를 띄운다
 */
fun View?.showKeyboard(delayMills: Long = 0) {
    this?.let { view ->
        view.requestFocus()
        view.postDelayed({
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                view,
                InputMethodManager.SHOW_IMPLICIT
            )
        }, delayMills)
    }
}

fun View?.showKeyboardWithoutFocus(delayMills: Long = 0) {
    this?.let { view ->
        view.postDelayed({
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                view,
                InputMethodManager.SHOW_IMPLICIT
            )
        }, delayMills)
    }
}


/**
 * 소프트 키보드를 숨긴다
 */
fun View?.hideKeyboard() {
    this?.let { view ->
        (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }
}

inline val View.isKeyboardShow: Boolean
    get() {
        val softKeyboardHeight = 100
        val r = Rect()
        this.getWindowVisibleDisplayFrame(r)
        val dm = this.resources.displayMetrics
        val heightDiff = this.bottom - r.bottom
        return heightDiff > softKeyboardHeight * dm.density
    }

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
inline val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

fun View.setScale(scale: Float) {
    this.scaleX = scale
    this.scaleY = scale
}

fun TextView.setColorOfSubstring(substring: String, color: Int) {
    try {
        val spannable = SpannableString(text)
        val start = text.indexOf(substring)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            start,
            start + substring.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannable
    } catch (e: Exception) {
        Log.d(
            "ViewExtensions",
            "exception in setColorOfSubstring, text=$text, substring=$substring",
            e
        )
    }
}

fun TextView.setBoldOfSubstring(substring: String) {
    try {
        val spannable = SpannableString(text)
        val start = text.indexOf(substring)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            start + substring.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannable
    } catch (e: Exception) {
        Log.d(
            "ViewExtensions",
            "exception in setBoldOfSubstring, text=$text, substring=$substring",
            e
        )
    }
}

fun TextView.setTextfromHtml(format: String, vararg args: Any) {
    text = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Html.fromHtml(String.format(format, *args))
    } else {
        Html.fromHtml(String.format(format, *args), Html.FROM_HTML_MODE_LEGACY)
    }
}

fun View.onSingleClick(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
//    setOnClickListener(OnSingleClickEvent(listener))
}

fun Group.addOnClickListener(listener: (view: View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }

}
fun File.deleteDirectory(): Boolean {
    return if (exists()) {
        listFiles()?.forEach {
            if (it.isDirectory) {
                it.deleteDirectory()
            } else {
                it.delete()
            }
        }
        delete()
    } else false
}
