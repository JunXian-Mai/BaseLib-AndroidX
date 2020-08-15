package org.markensic.baselibrary.impl.ui

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

interface BaseUiView {
    fun <T: View> View.bindView(@IdRes res: Int): Lazy<T> {
        return lazy { findViewById<T>(res) }
    }

    fun <T: View> Activity.bindView(@IdRes res: Int): Lazy<T> {
        return lazy { findViewById<T>(res) }
    }
}