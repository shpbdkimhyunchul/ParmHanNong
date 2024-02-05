package com.parmhannong

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

inline val Int.toResString: String
    get() = MainApplication.resources().getString(this)

inline val Int.toDrawable: Drawable?
    get() = AppCompatResources.getDrawable(
        MainApplication.applicationContext(),
        this
    )

fun Int.toResColor(view: TextView) {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        view.setTextColor(MainApplication.applicationContext().getColor(this))
    } else {
        view.setTextColor(ContextCompat.getColor(MainApplication.applicationContext(), this))
    }
}

inline val Int.toResColor: Int
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MainApplication.applicationContext().getColor(this)
        } else {
            ContextCompat.getColor(MainApplication.applicationContext(), this)
        }
    }

inline val Int.toResDrawable: Drawable?
    get() {
        return ContextCompat.getDrawable(MainApplication.applicationContext(), this)
    }

fun Int.toResStyle(view: TextView) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        view.setTextAppearance(this)
    } else {
        @Suppress("DEPRECATION")
        view.setTextAppearance(MainApplication.applicationContext(), this)
    }
}

fun Int.toResStyle(view: Button) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        view.setTextAppearance(this)
    } else {
        @Suppress("DEPRECATION")
        view.setTextAppearance(MainApplication.applicationContext(), this)
    }
}

inline val Int.toDimensionPixelOffset: Int
    get() = MainApplication.applicationContext().resources.getDimensionPixelOffset(
        this
    )

fun String.toMainColor(): String {
    return this.replace("#main_color", R.string.main_color.toResString)
}