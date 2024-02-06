package com.jml.github.challenge.ui.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun Context.isTablet(): Boolean {
    val xlarge =
        resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
    val large =
        resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
    return xlarge || large
}


fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    visibility = View.VISIBLE
                }
            }
        )
    } else {
        visibility = View.VISIBLE
    }
}

fun View.gone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    visibility = hidingStrategy
                }
            }
        )
    } else {
        visibility = hidingStrategy
    }
}

fun RecyclerView.moveScroll() {
    post {
        // Smooth scroll
        smoothScrollBy(0, 100);
    }
}