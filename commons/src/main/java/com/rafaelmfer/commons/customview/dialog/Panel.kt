package com.rafaelmfer.commons.customview.dialog

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window.FEATURE_NO_TITLE
import androidx.appcompat.app.AppCompatDialog
import com.rafaelmfer.commons.R
import java.io.InvalidClassException

fun View.newPanel(
    layout: Any = R.layout.view_panel,
    onShow: DialogInterface.OnShowListener? = null,
    init: (Panel.() -> Unit) = {}
) = Panel(context, onShow, init, layout).apply { show() }

class Panel(
    context: Context,
    onShow: DialogInterface.OnShowListener? = null,
    init: (Panel.() -> Unit),
    layout: Any
) : AppCompatDialog(context, R.style.Panel) {

    init {
        requestWindowFeature(FEATURE_NO_TITLE)
        when (layout) {
            is Int -> setContentView(layout)
            is View -> setContentView(layout)
            else -> throw InvalidClassException("layout must be resourceID or View")
        }
        init(this)
        setOnShowListener(onShow)
    }
}