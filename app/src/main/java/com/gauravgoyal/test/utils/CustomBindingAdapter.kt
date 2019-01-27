package com.gauravgoyal.test.utils


import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


@BindingConversion
fun intToStr(value: Int?): String {
    return value?.toString() ?: ""
}

@InverseBindingAdapter(attribute = "android:text")
fun captureIntValue(view: EditText): Int? {
    var value: Long = 0
    try {
        value = Integer.parseInt(view.text.toString()).toLong()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
    }

    return value.toInt()
}


@BindingAdapter("decorator")
fun setDecorator(view: RecyclerView, vertical: Boolean) {
    view.addItemDecoration( DividerItemDecoration(view.context, if(vertical)  DividerItemDecoration.VERTICAL else  DividerItemDecoration.HORIZONTAL))
}