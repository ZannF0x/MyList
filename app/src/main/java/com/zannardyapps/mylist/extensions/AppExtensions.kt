package com.zannardyapps.mylist.extensions

import java.text.SimpleDateFormat
import java.util.*

private val localeBrazil = Locale("pt", "BR")

fun Date.format(): String {
    return SimpleDateFormat("dd/MM/yyyy", localeBrazil).format(this)
}