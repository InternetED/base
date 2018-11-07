package com.internet.boy.androidbase.kutils

import java.util.*


val random: Random by lazy { Random() }

fun List<*>.randomElement(): String = if (this.isEmpty()) "" else this[random.nextInt(this.size)].toString()

fun Array<*>.randomElement(): String = if (this.isEmpty()) "" else this[random.nextInt(this.size)].toString()



