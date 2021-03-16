package com.example.githubusers.util.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.shortToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}