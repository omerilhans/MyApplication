package com.omerilhanli.myapplication.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("invoke")
fun View.invoke(function: () -> Unit) {
    function.invoke()
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.isRefreshing(isRefreshing: Boolean = true) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter("android:visibility")
fun View.visibility(visible: Boolean = true) {
    visibility =
        if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
}