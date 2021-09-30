package com.example.githubusers.framework

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.shortSnackBar(@StringRes message: Int) {

    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()

}

fun Fragment.shortSnackBar(message: String) {

    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()

}