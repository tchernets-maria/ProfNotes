package ru.profsoft.profnotes.core

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.core.text.toSpannable

fun CharSequence.spanString(startIndex: Int, endIndex: Int, color: Int): Spannable {
    val spannable = this.toSpannable()
    spannable.setSpan(
        ForegroundColorSpan(color),
        startIndex, endIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}

fun CharSequence.spanStringUnderline(startIndex: Int, endIndex: Int): Spannable {
    val spannable = this.toSpannable()
    spannable.setSpan(
        UnderlineSpan(),
        startIndex, endIndex,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
    )
    return spannable
}