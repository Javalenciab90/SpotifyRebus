package com.java90apps.spotifyrebus.domain.extensions

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun ProgressBar.hideProgressBar() {
    this.visibility = View.GONE
}

fun ProgressBar.showProgressBar() {
    this.visibility = View.VISIBLE
}

fun TextView.showTextError() {
    this.visibility = View.VISIBLE
}

fun TextView.hideTextError() {
    this.visibility = View.GONE
}

fun Button.showRetryButton() {
    this.visibility = View.VISIBLE
}

fun Button.hideRetryButton() {
    this.visibility = View.GONE
}

fun Fragment.navigateWithDirection(directions: NavDirections) {
    view?.findNavController()?.navigate(directions)
}


