package com.omerilhanli.myapplication.common

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

fun View.applyAnimation() {
    ScaleAnimation(
        0.5f, 1.0f, 0.5f, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f
    ).apply {
        duration = 500
    }.also {
        startAnimation(it)
    }
}