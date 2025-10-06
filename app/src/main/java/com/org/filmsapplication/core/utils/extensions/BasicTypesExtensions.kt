package com.org.filmsapplication.core.utils.extensions

fun Double.shorten(decimals: Int): Double {
    return String.format("%.$decimals" + "f", this).replace(',', '.').toDouble()
}