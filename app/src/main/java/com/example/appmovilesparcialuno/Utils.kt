package com.example.appmovilesparcialuno

import java.text.NumberFormat
import java.util.Locale

fun getNumberWithCurrency(money: Float): String {
    return NumberFormat.getCurrencyInstance(Locale("es", "AR")).format(money)
}