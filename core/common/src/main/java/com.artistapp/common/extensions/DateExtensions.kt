package com.artistapp.common.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDisplayFormat() = try {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    formatter.parse(this)?.let {
        SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(it)
    } ?: "Latest"
} catch (exception: ParseException) {
    "Latest"
}